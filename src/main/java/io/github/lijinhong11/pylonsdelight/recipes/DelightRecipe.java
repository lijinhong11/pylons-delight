package io.github.lijinhong11.pylonsdelight.recipes;

import io.github.pylonmc.pylon.core.recipe.PylonRecipe;
import io.github.pylonmc.pylon.core.recipe.RecipeInput;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public interface DelightRecipe extends PylonRecipe {
    default boolean matches(Collection<ItemStack> inputItems) {
        if (inputItems.size() != getInputs().size()) return false;
        List<ItemStack> recipeItems = getInputs().stream().map(i -> ((RecipeInput.Item) i).getItems().iterator().next().createItemStack()).toList();

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
