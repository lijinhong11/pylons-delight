package io.github.lijinhong11.pylonsdelight.objects;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.recipes.wok.WokRecipe;
import io.github.lijinhong11.pylonsdelight.util.DelightKeys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WokRecipes {
    private WokRecipes() {}

    private static final Map<NamespacedKey, WokRecipe> recipes = new ConcurrentHashMap<>();

    public static final WokRecipe TOMATO_WITH_EGGS = registerRecipe(WokRecipe.builder()
            .key(DelightKeys.DISH_TOMATO_WITH_EGGS)
            .item(1, ItemStack.of(Material.EGG))
            .item(5, DelightItems.TOMATO)
            .output(Dishes.TOMATO_WITH_EGGS)
            .build());

    public static WokRecipe registerRecipe(WokRecipe recipe) {
        recipes.put(recipe.key(), recipe);
        return recipe;
    }

    public static @Nullable WokRecipe getRecipe(String dishId) {
        return recipes.values().stream().filter(w -> w.output().dishId().equalsIgnoreCase(dishId)).findFirst().orElse(null);
    }

    public static List<WokRecipe> findRecipe(Map<Integer, ItemStack> progress) {
        return recipes.values().stream()
                .filter(recipe -> {
                    if (progress.size() > recipe.items().size()) {
                        return false;
                    }
                    for (Map.Entry<Integer, ItemStack> e : progress.entrySet()) {
                        ItemStack required = recipe.items().get(e.getKey());
                        if (required == null || !required.isSimilar(e.getValue())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();
    }
}
