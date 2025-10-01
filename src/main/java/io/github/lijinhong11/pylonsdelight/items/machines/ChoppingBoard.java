package io.github.lijinhong11.pylonsdelight.items.machines;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.recipes.CommonRecipeType;
import io.github.lijinhong11.pylonsdelight.recipes.subs.Chopping;
import io.github.lijinhong11.pylonsdelight.util.ComponentUtils;
import io.github.pylonmc.pylon.base.entities.SimpleItemDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.ItemDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import io.github.pylonmc.pylon.core.i18n.PylonArgument;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChoppingBoard extends PylonBlock implements PylonInteractBlock, PylonEntityHolderBlock {
    public static final CommonRecipeType<Chopping> RECIPE_TYPE = new CommonRecipeType<>(DelightKeys.CHOPPING_BOARD);

    private int cuts;
    private ItemStack item;
    private Chopping currentChopping;

    public ChoppingBoard(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        this.cuts = 0;
        this.item = null;

        addEntity("item", new SimpleItemDisplay(
                new ItemDisplayBuilder()
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.75)
                        )
                        .itemStack(ItemStack.of(Material.AIR))
                        .build(block.getLocation().toCenterLocation().subtract(0, 0.1, 0))
        ));
    }

    public ChoppingBoard(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        this.cuts = pdc.getOrDefault(DelightDataKeys.CUTS, PersistentDataType.INTEGER, 0);
        this.item = pdc.get(DelightDataKeys.ITEM, DataType.ITEM_STACK);
        this.currentChopping = RECIPE_TYPE.findRecipe(item);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) {
            e.setCancelled(true);
            return;
        }

        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack hand = inv.getItemInMainHand();

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (EquipmentSlot.HAND != e.getHand()) {
            return;
        }

        if (hand.isSimilar(DelightItems.KNIFE)) {
            if (item == null || item.getType().isAir()) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.no-item"));
                e.setCancelled(true);
                return;
            }

            if (cuts > currentChopping.maxCuts()) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.no-cut-more"));
                e.setCancelled(true);
                return;
            }

            cuts += 1;
            p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.chop", PylonArgument.of("cuts", cuts)));
            e.setCancelled(true);
            return;
        }

        if (hand.getType().isAir()) {
            if (item == null || item.getType().isAir()) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.no-item"));
                e.setCancelled(true);
                return;
            }

            if (inv.firstEmpty() == -1) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("inv-full"));
                e.setCancelled(true);
                return;
            }

            if (cuts == 0) {
                inv.addItem(item);
                item = null;
                ItemDisplay itemDisplay = (ItemDisplay) getHeldEntityOrThrow("item").getEntity();
                itemDisplay.setItemStack(ItemStack.of(Material.AIR));
                e.setCancelled(true);
                return;
            }

            inv.addItem(currentChopping.output().asQuantity(cuts + 1));
            item = null;
            cuts = 0;
            ItemDisplay itemDisplay = (ItemDisplay) getHeldEntityOrThrow("item").getEntity();
            itemDisplay.setItemStack(ItemStack.of(Material.AIR));
            e.setCancelled(true);
        } else {
            if (item != null && !item.getType().isAir()) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.already-placed"));
                e.setCancelled(true);
                return;
            }

            ItemStack dest = hand.asOne();

            Chopping chopping = RECIPE_TYPE.findRecipe(List.of(dest));
            if (chopping == null) {
                p.sendMessage(ComponentUtils.getTranslatableMessage("chopping_board.no-match"));
                e.setCancelled(true);
                return;
            }

            currentChopping = chopping;

            item = dest;
            cuts = 0;
            hand.setAmount(hand.getAmount() - 1);
            ItemDisplay itemDisplay = (ItemDisplay) getHeldEntityOrThrow("item").getEntity();
            itemDisplay.setItemStack(item);
            e.setCancelled(true);
        }
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        pdc.set(DelightDataKeys.CUTS, PersistentDataType.INTEGER, cuts);
        pdc.set(DelightDataKeys.ITEM, DataType.ITEM_STACK, item);
    }
}
