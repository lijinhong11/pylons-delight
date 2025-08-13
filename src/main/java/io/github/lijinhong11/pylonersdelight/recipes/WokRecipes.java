package io.github.lijinhong11.pylonersdelight.recipes;

import io.github.lijinhong11.pylonersdelight.recipes.wok.WokRecipe;
import io.github.lijinhong11.pylonersdelight.util.DelightKeys;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WokRecipes {
    private WokRecipes() {}

    private static final Map<String, WokRecipe> recipes = new ConcurrentHashMap<>();

    public static final WokRecipe TOMATO_WITH_EGGS = WokRecipe.builder()
            .key(DelightKeys.DISH_TOMATO_WITH_EGGS)
            .item()

    public static WokRecipe registerRecipe(WokRecipe recipe) {
        recipes.put(recipe.getOutput().dishId(), recipe);
        return recipe;
    }

    public static @Nullable WokRecipe getRecipe(String dishId) {
        return recipes.get(dishId);
    }

    public static List<WokRecipe> findRecipe(Map<Integer, ItemStack> progress) {
        return recipes.values().stream()
                .filter(recipe -> {
                    if (progress.size() > recipe.getItems().size()) {
                        return false;
                    }
                    for (Map.Entry<Integer, ItemStack> e : progress.entrySet()) {
                        ItemStack required = recipe.getItems().get(e.getKey());
                        if (required == null || !required.isSimilar(e.getValue())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();
    }
}
