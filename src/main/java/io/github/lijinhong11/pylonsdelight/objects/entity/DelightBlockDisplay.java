package io.github.lijinhong11.pylonsdelight.objects.entity;

import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.pylonmc.pylon.core.entity.PylonEntity;
import org.bukkit.entity.BlockDisplay;
import org.jetbrains.annotations.NotNull;

public class DelightBlockDisplay extends PylonEntity<BlockDisplay> {
    static {
        PylonEntity.register(DelightKeys.ENTITY, BlockDisplay.class, DelightBlockDisplay.class);
    }

    public DelightBlockDisplay(@NotNull BlockDisplay entity) {
        super(DelightKeys.ENTITY, entity);
    }
}
