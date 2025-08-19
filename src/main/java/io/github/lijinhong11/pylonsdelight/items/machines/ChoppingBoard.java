package io.github.lijinhong11.pylonsdelight.items.machines;

import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class ChoppingBoard extends PylonBlock implements PylonInteractableBlock, PylonEntityHolderBlock {
    public ChoppingBoard(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
    }

    public ChoppingBoard(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {

    }
}
