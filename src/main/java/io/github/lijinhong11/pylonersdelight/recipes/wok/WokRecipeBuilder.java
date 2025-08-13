package io.github.lijinhong11.pylonersdelight.recipes.wok;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class WokRecipeBuilder {
    private final Map<Integer, ItemStack> items;
    private NamespacedKey key;
    private Dish dish;

    WokRecipeBuilder() {
        items = new HashMap<>();
    }

    public WokRecipeBuilder key(NamespacedKey key) {
        this.key = key;
        return this;
    }

    public WokRecipeBuilder item(int seconds, ItemStack item) {
        this.items.put(seconds, item);
        return this;
    }

    public WokRecipeBuilder output(Dish dish) {
        this.dish = dish;
        return this;
    }

    public WokRecipe build() {
        return new WokRecipe(key, items, dish);
    }
}
