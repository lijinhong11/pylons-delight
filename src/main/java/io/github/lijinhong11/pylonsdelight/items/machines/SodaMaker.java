package io.github.lijinhong11.pylonsdelight.items.machines;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.objects.entity.DelightBlockDisplay;
import io.github.lijinhong11.pylonsdelight.recipes.CommonRecipeType;
import io.github.lijinhong11.pylonsdelight.recipes.subs.SodaRecipe;
import io.github.lijinhong11.pylonsdelight.util.FacedLocation;
import io.github.pylonmc.pylon.base.entities.SimpleItemDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.display.BlockDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.ItemDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.List;

public class SodaMaker extends PylonBlock implements PylonInteractBlock, PylonTickingBlock, PylonEntityHolderBlock {
    public static final CommonRecipeType<SodaRecipe> RECIPE_TYPE = new CommonRecipeType<>(DelightKeys.SODA_MAKER);

    private int ticks;
    private ItemStack item;

    public SodaMaker(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        ticks = 0;

        BlockFace face = BlockFace.NORTH;
        if (context instanceof BlockCreateContext.PlayerPlace p) {
            face = p.getPlayer().getFacing();
        }
        setupEntities(block.getLocation(), face);
    }

    public SodaMaker(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
        item = pdc.get(DelightDataKeys.ITEM, DataType.ITEM_STACK);
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack hand = inv.getItemInMainHand();

        if (hand.getType().isAir()) {

        } else {
            SodaRecipe recipe = RECIPE_TYPE.findRecipe(List.of(hand));
            if (recipe != null) {

            } else {

            }
        }

        e.setCancelled(true);
    }

    @Override
    public void tick(double deltaSeconds) {
        if (ticks > 0) {

            ticks++;
        }
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        pdc.set(DelightDataKeys.TICKS, PersistentDataType.INTEGER, ticks);
        pdc.set(DelightDataKeys.ITEM, DataType.ITEM_STACK, item);
    }

    private void setupEntities(Location block, BlockFace face) {
        Location center = block.toCenterLocation().subtract(0, 0.5, 0);
        addEntity("bottom", new DelightBlockDisplay(
                new BlockDisplayBuilder()
                        .material(Material.WHITE_CONCRETE)
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.95, 0.1, 0.95)
                        )
                        .build(center)
                )
        );

        addEntity("hold", new DelightBlockDisplay(
                new BlockDisplayBuilder()
                        .material(Material.WHITE_CONCRETE)
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.2, 0.95, 0.2)
                        )
                        .build(new FacedLocation(center, face).inFront(0.35).add(0, 0.15, 0))
        ));

        Quaternionf connect = new Quaternionf();
        if (face.toString().contains("WEST") || face.toString().contains("EAST")) {
            connect = connect.rotateZ((float) Math.toRadians(90));
        }

        addEntity("connect", new DelightBlockDisplay(
                new BlockDisplayBuilder()
                        .material(Material.WHITE_CONCRETE)
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.2, 0.2, 0.55)
                                        .rotate(connect)
                        )
                        .build(new FacedLocation(center, face).inFront(0.175).add(0, 0.725, 0))
        ));

        addEntity("inject", new DelightBlockDisplay(
                new BlockDisplayBuilder()
                        .material(Material.GRAY_CONCRETE)
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.1, 0.05, 0.1)
                        )
                        .build(center.clone().add(0, 0.6, 0))
        ));

        addEntity("bottle", new SimpleItemDisplay(
                new ItemDisplayBuilder()
                        .itemStack(ItemStack.of(Material.GLASS_BOTTLE))
                        .transformation(
                                new TransformBuilder()
                                        .scale(0.4)
                                        .rotate(connect)
                        )
                        .build(center.clone().add(0, 0.425, 0))
        ));
    }
}
