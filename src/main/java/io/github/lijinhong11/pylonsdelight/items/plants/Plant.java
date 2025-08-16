package io.github.lijinhong11.pylonsdelight.items.plants;

import io.github.lijinhong11.pylonsdelight.objects.PlantStage;
import io.github.lijinhong11.pylonsdelight.objects.map.FastFloorKeyMap;
import io.github.lijinhong11.pylonsdelight.util.DelightDataKeys;
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
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Plant extends PylonBlock implements PylonTickingBlock, PylonInteractableBlock {
    protected final FastFloorKeyMap<PlantStage> stages = new FastFloorKeyMap<>();

    protected int ticks = 0;
    protected PlantStage currentStage;

    private int finalStageTicks;

    public Plant(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);

        setup();
    }

    public Plant(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
        setup();
    }

    @Override
    public void tick(double deltaSeconds) {
        if (ticks < finalStageTicks) {
            PlantStage stage = stages.get(ticks);
            if (stage != null) {
                stage.edits().accept(getBlock());
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

        Block block = e.getClickedBlock();
        World world = block.getWorld();
        if (action.isRightClick()) {
            if (currentStage != stages.values().toArray()[0]) {
                world.dropItem(block.getLocation(), currentStage.itemStack());

                this.ticks = 0;
                block.setType(Material.OAK_SAPLING);
            }
        }
    }

    @Override
    public @NotNull WailaConfig getWaila(@NotNull Player player) {
        PylonArgument name = PylonArgument.of("name", getItemName());
        PylonArgument percent = PylonArgument.of("percent", currentStage.percent());
        return new WailaConfig(Component.text("waila.plant"), List.of(name, percent), BossBar.Color.GREEN, BossBar.Overlay.PROGRESS, currentStage.percent() / 100f);
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        pdc.set(DelightDataKeys.TICKS, PersistentDataType.INTEGER, ticks);
    }

    private void setup() {
        setTickInterval(20);

        addStages();

        Integer[] stagesArr = stages.keySet().toArray(new Integer[0]);
        finalStageTicks = stagesArr[stagesArr.length - 1];

        currentStage = stages.get(stages.floorKey(ticks));
        if (currentStage == null) {
            currentStage = stages.values().toArray(PlantStage[]::new)[0];
        }

        currentStage.edits().accept(getBlock());
    }

    //abstract methods
    protected abstract void addStages();

    protected abstract Component getItemName();
}
