package io.github.lijinhong11.pylonersdelight.items.wok;

import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.entity.PylonEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class Wok extends PylonBlock implements PylonEntityHolderBlock, PylonTickingBlock, PylonInteractableBlock {
    public Wok(@NotNull Block block) {
        super(block);

        setTickInterval(20);

        addEntity("pan", createPan());
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {

    }

    @Override
    public void tick(double deltaSeconds) {

    }

    private PylonEntity<BlockDisplay> createPan() {
        return
    }
}
