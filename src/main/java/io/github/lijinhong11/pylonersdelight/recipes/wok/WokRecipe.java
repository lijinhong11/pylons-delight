package io.github.lijinhong11.pylonersdelight.recipes.wok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class WokRecipe implements Iterable<Map.Entry<Integer, ItemStack>> {
    private final NamespacedKey key;
    private final Map<Integer, ItemStack> items;
    private final Dish output;

    public static WokRecipeBuilder builder() {
        return new WokRecipeBuilder();
    }

    @Override
    public @NotNull Iterator<Map.Entry<Integer, ItemStack>> iterator() {
        return items.entrySet().iterator();
    }
}
