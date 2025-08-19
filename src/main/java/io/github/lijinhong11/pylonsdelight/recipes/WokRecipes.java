package io.github.lijinhong11.pylonsdelight.recipes;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.recipes.wok.WokRecipe;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WokRecipes {
    private WokRecipes() {}

    private static final Map<NamespacedKey, WokRecipe> recipes = new ConcurrentHashMap<>();

    public static final WokRecipe TOMATO_WITH_EGGS = registerRecipe(WokRecipe.builder()
            .key(DelightKeys.DISH_TOMATO_WITH_EGGS)
            .items(Map.of(
                    0, ItemStack.of(Material.EGG),
                    10, DelightItems.TOMATO
            ))
            .output(Dishes.TOMATO_WITH_EGGS)
            .requiredSeconds(5)
            .requiredStirs(3)
            .build());

    public static WokRecipe registerRecipe(WokRecipe recipe) {
        recipes.put(recipe.key(), recipe);
        return recipe;
    }

    public static boolean findRecipe(Collection<ItemStack> inputItems) {
        return findRecipe(inputItems.stream().toList());
    }

    public static boolean findRecipe(List<ItemStack> inputItems) {
        return getRecipe(inputItems) != null;
    }

    public static WokRecipe getRecipe(List<ItemStack> inputItems) {
        for (WokRecipe recipe : recipes.values()) {
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
