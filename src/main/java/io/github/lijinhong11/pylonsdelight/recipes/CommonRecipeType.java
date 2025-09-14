package io.github.lijinhong11.pylonsdelight.recipes;

import io.github.pylonmc.pylon.core.recipe.RecipeType;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class CommonRecipeType<T extends DelightRecipe> extends RecipeType<T> {
    public CommonRecipeType(@NotNull NamespacedKey key) {
        super(key);
    }

    public boolean gotRecipe(Collection<ItemStack> inputItems) {
        return findRecipe(inputItems) != null;
    }

    public @Nullable T findRecipe(ItemStack inputItem) {
        return findRecipe(List.of(inputItem));
    }

    public @Nullable T findRecipe(Collection<ItemStack> inputItems) {
        for (T t : this) {
            if (t.matches(inputItems)) {
                return t;
            }
        }

        return null;
    }
}
