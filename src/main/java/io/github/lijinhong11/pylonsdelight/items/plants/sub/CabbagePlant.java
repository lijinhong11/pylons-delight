package io.github.lijinhong11.pylonsdelight.items.plants.sub;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.items.plants.Plant;
import io.github.lijinhong11.pylonsdelight.objects.plant.PlantStage;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class CabbagePlant extends Plant {
    public CabbagePlant(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
    }

    public CabbagePlant(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    @Override
    protected void addStages() {
        stages.put(30, new PlantStage(100, DelightItems.CABBAGE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRhZDY2MTU2MjhkNjUwNjNmNDdjZThjNjljOTIyNGIxZDkyMmNlN2MyZGQ5NTNmN2YxMmIxMTBkMDJiYmVmNSJ9fX0="));
    }

    @Override
    protected Component getItemName() {
        return DelightItems.CABBAGE.displayName();
    }

    @Override
    protected int getOutputCount(PlantStage stage) {
        return 1;
    }
}
