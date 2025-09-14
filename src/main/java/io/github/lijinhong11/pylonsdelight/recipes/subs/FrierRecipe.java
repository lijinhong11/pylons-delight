package io.github.lijinhong11.pylonsdelight.recipes.subs;

import io.github.lijinhong11.pylonsdelight.recipes.DelightRecipe;
import io.github.pylonmc.pylon.core.recipe.FluidOrItem;
import lombok.Builder;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;

import java.util.List;

@Builder(toBuilder = true)
public class FrierRecipe implements DelightRecipe {
    @Override
    public @NotNull List<FluidOrItem> getInputs() {
        return List.of();
    }

    @Override
    public @NotNull List<FluidOrItem> getResults() {
        return List.of();
    }

    @Override
    public @NotNull Gui display() {
        return null;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return null;
    }
}
