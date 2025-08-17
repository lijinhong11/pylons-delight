package io.github.lijinhong11.pylonsdelight.items.plants.sub;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.items.plants.Plant;
import io.github.lijinhong11.pylonsdelight.objects.PlantStage;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CucumberPlant extends Plant {
    public CucumberPlant(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
    }

    public CucumberPlant(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    @Override
    protected void addStages() {
        stages.put(30, new PlantStage(100, DelightItems.CUCUMBER, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdlNDFlNThkNjcwNDMzM2RkMmJiNTgwYmViMTBkNTcxMzgyMjY0ZTVmOGVhMmM5OTkwZmI1MDI0YTg0N2VlNiJ9fX0="));
    }

    @Override
    protected Component getItemName() {
        return DelightItems.CUCUMBER.displayName();
    }

    @Override
    protected int getOutputCount(PlantStage stage) {
        return new Random().nextInt(2, 5);
    }
}
