package io.github.lijinhong11.pylonsdelight.items.machines;

import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.recipes.CommonRecipeType;
import io.github.lijinhong11.pylonsdelight.recipes.subs.FrierRecipe;
import io.github.pylonmc.pylon.base.entities.SimpleTextDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.TextDisplayBuilder;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Frier extends PylonBlock implements PylonInteractBlock, PylonTickingBlock, PylonEntityHolderBlock {
    public static final CommonRecipeType<FrierRecipe> RECIPE_TYPE = new CommonRecipeType<>(DelightKeys.FRIER);

    private int ticks;

    public Frier(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        ticks = 0;
        addEntity("fluid", createFluidDisplay(block.getLocation()));
    }

    public Frier(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND) {
            Player p = e.getPlayer();
            PlayerInventory inv = p.getInventory();
            World w = p.getWorld();
            ItemStack hand = inv.getItemInMainHand();

            if (!hand.isEmpty())
            if (Tag.ITEMS_BREAKS_DECORATED_POTS.isTagged(hand.getType())) {
                return;
            }
        }
    }

    @Override
    public void tick(double deltaSeconds) {

    }

    private SimpleTextDisplay createFluidDisplay(Location loc) {
        Location block = loc.toCenterLocation().add(0, 0.1, 0);
        TextDisplay display = new TextDisplayBuilder()
                .billboard(Display.Billboard.FIXED)
                .build(block);
        display.setTextOpacity((byte) 4);
        return new SimpleTextDisplay(display);
    }
}
