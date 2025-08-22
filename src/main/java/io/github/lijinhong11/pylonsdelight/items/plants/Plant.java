package io.github.lijinhong11.pylonsdelight.items.plants;

import io.github.lijinhong11.pylonsdelight.objects.plant.PlantStage;
import io.github.lijinhong11.pylonsdelight.util.map.FastFloorKeyMap;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.block.waila.WailaConfig;
import io.github.pylonmc.pylon.core.i18n.PylonArgument;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Plant extends PylonBlock implements PylonTickingBlock, PylonInteractableBlock {
    protected final FastFloorKeyMap<PlantStage> stages = new FastFloorKeyMap<>();

    private int ticks = 0;
    private PlantStage currentStage;

    private BlockFace rotate = BlockFace.NORTH;

    public Plant(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        if (context instanceof BlockCreateContext.PlayerPlace p) {
            rotate = p.getPlayer().getFacing().getOppositeFace();
        }
        setup();
    }

    public Plant(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
        rotate = BlockFace.values()[pdc.getOrDefault(DelightDataKeys.DIRECTION, PersistentDataType.INTEGER, 0)];
        setup();
    }

    @Override
    public void tick(double deltaSeconds) {
        if (currentStage != stages.values().toArray()[0]) {
            PlantStage stage = stages.get(ticks);
            if (stage != null) {
                stage.edits().accept(getBlock());
                BlockData data = getBlock().getBlockData();
                if (data instanceof Rotatable r) {
                    r.setRotation(rotate);
                }
                currentStage = stage;
            }
            ticks++;
        }
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        Action action = e.getAction();
        if (e.getClickedBlock() == null) {
            return;
        }

        if (currentStage == null) {
            return;
        }

        Block block = e.getClickedBlock();
        World world = block.getWorld();
        if (action.isRightClick()) {
            if (currentStage.itemStack() != null) {
                world.dropItem(block.getLocation(), currentStage.itemStack().asQuantity(getOutputCount(currentStage)));

                this.ticks = 0;
                this.currentStage = null;
                block.setType(Material.OAK_SAPLING);
            }
        }
    }

    @Override
    public @NotNull WailaConfig getWaila(@NotNull Player player) {
        float percent = 0f;

        if (currentStage != null) {
            percent = currentStage.percent();
        }

        PylonArgument name = PylonArgument.of("name", getItemName());
        PylonArgument percentArg = PylonArgument.of("percent", percent);
        return new WailaConfig(Component.translatable("pylon.pylons-delight.waila.plant"), List.of(name, percentArg), BossBar.Color.GREEN, BossBar.Overlay.PROGRESS, percent / 100f);
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        pdc.set(DelightDataKeys.TICKS, PersistentDataType.INTEGER, ticks);
        pdc.set(DelightDataKeys.DIRECTION, PersistentDataType.INTEGER, rotate.ordinal());
    }

    private void setup() {
        setTickInterval(20);

        addStages();

        currentStage = stages.get(stages.floorKey(ticks));
        if (currentStage != null) {
            currentStage.edits().accept(getBlock());
        }
    }

    public void setToLastStage() {
        PlantStage[] stages = this.stages.values().toArray(new PlantStage[0]);
        currentStage = stages[stages.length - 1];
    }

    //abstract methods
    protected abstract void addStages();

    protected abstract Component getItemName();

    protected abstract int getOutputCount(PlantStage stage);
}
