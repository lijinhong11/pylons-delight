package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.recipes.subs.WokRecipe;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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


}
