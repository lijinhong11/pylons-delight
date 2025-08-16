package io.github.lijinhong11.pylonsdelight.recipes.wok;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public record WokRecipe(NamespacedKey key, Map<Integer, ItemStack> items,
                        Dish output) implements Iterable<Map.Entry<Integer, ItemStack>> {
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public @NotNull Iterator<Map.Entry<Integer, ItemStack>> iterator() {
        return items.entrySet().iterator();
    }

    public static class Builder {
        private final Map<Integer, ItemStack> items;
        private NamespacedKey key;
        private Dish dish;

        Builder() {
            items = new HashMap<>();
        }

        public Builder key(NamespacedKey key) {
            this.key = key;
            return this;
        }

        public Builder item(int ticks, ItemStack item) {
            this.items.put(ticks, item);
            return this;
        }

        public Builder output(Dish dish) {
            this.dish = dish;
            return this;
        }

        public WokRecipe build() {
            if (key == null) {
                throw new IllegalArgumentException("wok recipe key is null");
            }

            if (dish == null) {
                throw new IllegalArgumentException("wok recipe output is null");
            }

            return new WokRecipe(key, items, dish);
        }
    }
}
