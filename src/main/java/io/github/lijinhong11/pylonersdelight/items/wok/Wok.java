package io.github.lijinhong11.pylonersdelight.items.wok;

import io.github.lijinhong11.pylonersdelight.util.DelightKeys;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.entity.PylonEntity;
import io.github.pylonmc.pylon.core.entity.display.BlockDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class Wok extends PylonBlock implements PylonEntityHolderBlock, PylonTickingBlock, PylonInteractableBlock {
    public Wok(@NotNull Block block) {
        super(block);

        setTickInterval(20);

        addEntity("pan", new Pan(block.getLocation()));
    }

    static {
        PylonBlock.register(DelightKeys.BLOCK_WOK, Material.STRUCTURE_VOID, Wok.class);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {

    }

    @Override
    public void tick(double deltaSeconds) {

    }

    private static class Pan extends PylonEntity<BlockDisplay> {
        public Pan(Location loc) {
            super(DelightKeys.ENTITY_PAN, new BlockDisplayBuilder()
                    .material(Material.BLACK_CARPET)
                    .brightness(1)
                    .transformation(new TransformBuilder().scale(0.75))
                    .build(loc));
        }
    }
}
