package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.items.machines.Wok;
import io.github.lijinhong11.pylonsdelight.recipes.wok.WokRecipe;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class WokRecipes {
    private WokRecipes() {}

    public static final WokRecipe TOMATO_WITH_EGGS = WokRecipe.builder()
            .key(DelightKeys.DISH_TOMATO_WITH_EGGS)
            .items(Map.of(
                    0, ItemStack.of(Material.EGG),
                    10, DelightItems.TOMATO
            ))
            .output(Dishes.TOMATO_WITH_EGGS)
            .requiredSeconds(5)
            .requiredStirs(3)
            .build();

    public static void registerDefaultRecipe() {
        registerRecipe(TOMATO_WITH_EGGS);
    }

    public static void registerRecipe(WokRecipe recipe) {
        Wok.RECIPE_TYPE.addRecipe(recipe);
    }

    public static boolean findRecipe(Collection<ItemStack> inputItems) {
        return findRecipe(inputItems.stream().toList());
    }

    public static boolean findRecipe(List<ItemStack> inputItems) {
        return getRecipe(inputItems) != null;
    }

    public static WokRecipe getRecipe(List<ItemStack> inputItems) {
        for (WokRecipe recipe : Wok.RECIPE_TYPE) {
            if (recipe.matches(inputItems)) {
                return recipe;
            }
        }

        return null;
    }

    public static boolean checkDone(List<ItemStack> inputItems, int ticks) {
        WokRecipe recipe = getRecipe(inputItems);
        return recipe != null && recipe.isDone(ticks);
    }

    public static ItemStack getResult(List<ItemStack> inputItems) {
        WokRecipe recipe = getRecipe(inputItems);
        return recipe != null ? recipe.output().getItem() : null;
    }
}
