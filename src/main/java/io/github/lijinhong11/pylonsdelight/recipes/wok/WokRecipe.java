package io.github.lijinhong11.pylonsdelight.recipes.wok;

import lombok.Builder;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record WokRecipe(NamespacedKey key, Map<Integer, ItemStack> items,
                        Dish output, int requiredSeconds, int requiredStirs) implements Iterable<Map.Entry<Integer, ItemStack>> {
    @Override
    public @NotNull Iterator<Map.Entry<Integer, ItemStack>> iterator() {
        return items.entrySet().iterator();
    }

    public boolean matches(List<ItemStack> inputItems) {
        if (inputItems.size() != items.size()) return false;
        List<ItemStack> recipeItems = new ArrayList<>(items.values());

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

    public boolean isDone(int seconds) {
        return seconds >= requiredSeconds;
    }
}
