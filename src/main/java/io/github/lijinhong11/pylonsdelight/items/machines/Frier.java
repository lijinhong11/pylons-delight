package io.github.lijinhong11.pylonsdelight.items.machines;

import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.recipes.CommonRecipeType;
import io.github.lijinhong11.pylonsdelight.recipes.subs.FrierRecipe;
import io.github.pylonmc.pylon.base.entities.SimpleTextDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.TextDisplayBuilder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

//We need 1.21.9!
public class Frier extends PylonBlock implements PylonInteractableBlock, PylonTickingBlock, PylonEntityHolderBlock {
    public static final CommonRecipeType<FrierRecipe> RECIPE_TYPE = new CommonRecipeType<>(DelightKeys.FRIER);

    public Frier(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        addEntity("fluid", createFluidDisplay(block.getLocation()));
    }

    public Frier(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {

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
