package io.github.lijinhong11.pylonsdelight.listeners;

import io.github.pylonmc.pylon.core.block.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

public class PlantListener implements Listener {
    @EventHandler
    public void onSaplingGrow(StructureGrowEvent e) {
        Block block = e.getLocation().getBlock();
        if (BlockStorage.isPylonBlock(block)) {
            e.setCancelled(true);
        }
    }
}
