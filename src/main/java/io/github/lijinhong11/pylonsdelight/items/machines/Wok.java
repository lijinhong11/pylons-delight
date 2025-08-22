package io.github.lijinhong11.pylonsdelight.items.machines;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.objects.entity.DelightBlockDisplay;
import io.github.lijinhong11.pylonsdelight.recipes.general.WokRecipes;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.lijinhong11.pylonsdelight.util.ComponentUtils;
import io.github.lijinhong11.pylonsdelight.util.EntityUtils;
import io.github.pylonmc.pylon.base.entities.SimpleItemDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.BlockDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.ItemDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import io.github.pylonmc.pylon.core.item.PylonItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wok extends PylonBlock implements PylonEntityHolderBlock, PylonTickingBlock, PylonInteractableBlock {
    private List<ItemStack> items = new ArrayList<>();

    private int ticks;
    private int stirs;

    public Wok(@NotNull Block block, @NotNull BlockCreateContext ctx) {
        super(block, ctx);

        BlockFace face = BlockFace.NORTH;
        if (ctx instanceof BlockCreateContext.PlayerPlace p) {
            face = p.getPlayer().getFacing();
        }
        setup(block, face);
    }

    public Wok(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        this.ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
        this.stirs = pdc.getOrDefault(DelightDataKeys.STIRS, PersistentDataType.INTEGER, 0);
        this.items = Arrays.stream(pdc.getOrDefault(DelightDataKeys.ITEMS, DataType.ITEM_STACK_ARRAY, new ItemStack[]{})).toList();
    }

    private void setup(Block block, BlockFace face) {
        setTickInterval(20);

        Location loc = block.getLocation();

        addEntity("pan", createPan(loc));
        addEntity("stick", createStickEntity(loc, face));
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        if (EquipmentSlot.HAND != e.getHand()) {
            return;
        }

        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack hand = inv.getItemInMainHand();
        World world = p.getWorld();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PylonItem pylon = PylonItem.fromStack(hand);
            if (pylon != null) {
                if (pylon.getKey().getKey().startsWith("dish")) {
                    return;
                }
            }

            if (hand.isSimilar(DelightItems.KNIFE)
                    || hand.isSimilar(DelightItems.CHOPPING_BOARD)
                    || hand.isSimilar(DelightItems.SODA_MAKER)
            ) {
                return;
            }

            if (p.isSneaking() && hand.getType().isAir()) {
                for (ItemStack i : items) {
                    inv.addItem(i);
                }
                items.clear();
                return;
            }

            if (hand.isSimilar(DelightItems.PLATE)) {
                if (ticks == 0) {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-dish"));
                    e.setCancelled(true);
                    return;
                }
                
                if (WokRecipes.checkDone(items, ticks)) {
                    ItemStack result = WokRecipes.getResult(items);
                    if (result != null) {
                        int amount = hand.getAmount();
                        if (amount > 1) {
                            if (inv.firstEmpty() == -1) {
                                p.sendMessage(ComponentUtils.getTranslatableMessage("inv-full"));
                                e.setCancelled(true);
                                return;
                            }

                            hand.setAmount(amount - 1);
                            inv.addItem(result);
                        } else {
                            inv.setItemInMainHand(result);
                        }

                        ticks = 0;
                        stirs = 0;
                        items.clear();
                        e.setCancelled(true);
                    }
                    return;
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-stir-yet"));
                }
                return;
            }

            if (hand.isSimilar(DelightItems.SLICE)) {
                if (WokRecipes.findRecipe(items)) {
                    if (stirs == 0) {
                        stirs = 1;
                        p.sendMessage(ComponentUtils.getTranslatableMessage("wok.start-stir"));
                    } else {
                        stirs += 1;
                        p.sendMessage(ComponentUtils.getTranslatableMessage("wok.stir"));
                    }
                    e.setCancelled(true);
                    return;
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-match"));
                    e.setCancelled(true);
                }
                return;
            }

            if (!hand.getType().isAir()) {
                int slot = items.size();
                if (slot < 6) {
                    items.add(hand.clone().asOne());
                    hand.setAmount(hand.getAmount() - 1);
                    ticks = 1;
                    world.spawnParticle(Particle.SMOKE, getBlock().getLocation(), 5);
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.full"));
                }

                e.setCancelled(true);
            }
        }
    }

    @Override
    public void tick(double deltaSeconds) {
        if (ticks > 0) {
            if (WokRecipes.checkDone(items, ticks)) {
                ticks = Math.max(ticks, 1);
                return;
            }
            ticks += 1;
        }
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        super.write(pdc);
        pdc.set(DelightDataKeys.TICKS, PersistentDataType.INTEGER, ticks);
        pdc.set(DelightDataKeys.STIRS, PersistentDataType.INTEGER, stirs);
        pdc.set(DelightDataKeys.ITEMS, DataType.ITEM_STACK_ARRAY, items.toArray(ItemStack[]::new));
    }

    private DelightBlockDisplay createPan(Location loc) {
        Location block = loc.toCenterLocation();
        block.subtract(0, 0.1, 0);
        return new DelightBlockDisplay(new BlockDisplayBuilder()
                .material(Material.BLACK_CARPET)
                .brightness(1)
                .transformation(new TransformBuilder().scale(0.8))
                .build(block));
    }

    private SimpleItemDisplay createStickEntity(Location loc, BlockFace face) {
        Location block = EntityUtils.moveBlockByFacing(loc.toCenterLocation(), face);
        block.subtract(-0.001, 0.485, -0.5);

        Quaternionf quaternionf = EntityUtils.getQuaternionForRotation(EntityUtils.BACK90_RIGHT45, face);

        return new SimpleItemDisplay(new ItemDisplayBuilder()
                .transformation(
                        new TransformBuilder()
                                .scale(0.45, 0.3, 0.45)
                                .rotate(quaternionf)
                )
                .itemStack(ItemStack.of(Material.STICK))
                .build(block));
    }
}