package io.github.lijinhong11.pylonsdelight.items.plants.sub;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.items.plants.Plant;
import io.github.lijinhong11.pylonsdelight.objects.plant.PlantStage;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TomatoPlant extends Plant {
    public TomatoPlant(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
    }

    public TomatoPlant(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    @Override
    protected void addStages() {
        stages.put(10, new PlantStage(50, DelightItems.IMMATURE_TOMATO, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllN2MyMTY3MzI4M2NlNjMwMDExY2M1MzgyNDYxMDg4MmNkZTNkOWE4YjYzMGMwZTIzMDU4ZTMxNDRkYiJ9fX0="));
        stages.put(25, new PlantStage(100, DelightItems.TOMATO, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiNzUyZTUyMzJiMDM5YjFlNzVlNDU0MTgzYTE5MmQ0MDU3YjdjYTgzMmY3YzI0YTVmZDg2Nzk2OWNiNGQifX19"));
    }

    @Override
    protected Component getItemName() {
        return DelightItems.TOMATO.displayName();
    }

    @Override
    protected int getOutputCount(PlantStage stage) {
        if (DelightItems.IMMATURE_TOMATO.isSimilar(stage.itemStack())) {
            return 1;
        }

        return new Random().nextInt(1, 3);
    }
}
