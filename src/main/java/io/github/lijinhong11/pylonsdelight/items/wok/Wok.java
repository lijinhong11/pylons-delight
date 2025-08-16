package io.github.lijinhong11.pylonsdelight.items.wok;

import io.github.lijinhong11.pylonsdelight.util.DelightKeys;
import io.github.pylonmc.pylon.base.entities.SimpleItemDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.PylonEntity;
import io.github.pylonmc.pylon.core.entity.display.BlockDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.ItemDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

public class Wok extends PylonBlock implements PylonEntityHolderBlock, PylonTickingBlock, PylonInteractableBlock {
    public Wok(@NotNull Block block, @NotNull BlockCreateContext ctx) {
        super(block, ctx);

        setup(block);
    }

    public Wok(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    private void setup(Block block) {
        setTickInterval(20);

        Location loc = block.getLocation();

        addEntity("pan", createPan(loc));
        addEntity("stick", createStickEntity(loc));
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent event) {

    }

    @Override
    public void tick(double deltaSeconds) {

    }

    private Pan createPan(Location loc) {
        Location block = loc.toCenterLocation();
        return new Pan(new BlockDisplayBuilder()
                .material(Material.BLACK_CARPET)
                .brightness(1)
                .transformation(new TransformBuilder().scale(0.75))
                .build(block));
    }

    private SimpleItemDisplay createStickEntity(Location loc) {
        Location block = loc.toCenterLocation();
        block.subtract(0, 0.5, 0.5);
        return new SimpleItemDisplay(new ItemDisplayBuilder()
                .transformation(
                        new TransformBuilder()
                                .scale(0.5)
                                .rotate(new Quaternionf(0.5, 0, -0.25, 0.65))
                )
                .itemStack(ItemStack.of(Material.STICK))
                .build(block));
    }

    public static class Pan extends PylonEntity<BlockDisplay> {
        public Pan(@NotNull BlockDisplay entity) {
            super(DelightKeys.ENTITY_PAN, entity);
        }

        public Pan(@NotNull NamespacedKey key, @NotNull BlockDisplay entity) {
            super(key, entity);
        }

        static {
            PylonEntity.register(DelightKeys.ENTITY_PAN, BlockDisplay.class, Pan.class);
        }
    }
}
