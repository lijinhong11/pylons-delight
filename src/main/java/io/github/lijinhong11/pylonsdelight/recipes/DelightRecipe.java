package io.github.lijinhong11.pylonsdelight.recipes;

import io.github.pylonmc.pylon.core.recipe.FluidOrItem;
import io.github.pylonmc.pylon.core.recipe.PylonRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public interface DelightRecipe extends PylonRecipe {
    default boolean matches(Collection<ItemStack> inputItems) {
        if (inputItems.size() != getInputs().size()) return false;
        List<ItemStack> recipeItems = getInputs().stream().map(i -> ((FluidOrItem.Item) i).item()).toList();

        for (ItemStack stack : recipeItems) {
            boolean found = false;
            for (ItemStack input : inputItems) {
                if (input.isSimilar(stack) && input.getAmount() >= stack.getAmount()) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        return true;
    }
}
