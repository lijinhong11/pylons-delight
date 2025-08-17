package io.github.lijinhong11.pylonsdelight.items.machines;

import io.github.pylonmc.pylon.base.entities.SimpleTextDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.TextDisplayBuilder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

//We need 1.21.9!
public class Frier extends PylonBlock implements PylonInteractableBlock, PylonTickingBlock, PylonEntityHolderBlock {
    public Frier(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
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
        Location block = loc.toCenterLocation();
        TextDisplay display =  new TextDisplayBuilder()
                .build(block);
        display.setTextOpacity((byte) 4);
        return new SimpleTextDisplay(display);
    }
}
