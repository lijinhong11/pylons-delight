package io.github.lijinhong11.pylonsdelight.recipes.wok;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.pylonmc.pylon.core.guide.button.ItemButton;
import io.github.pylonmc.pylon.core.recipe.FluidOrItem;
import io.github.pylonmc.pylon.core.recipe.PylonRecipe;
import io.github.pylonmc.pylon.core.util.gui.GuiItems;
import lombok.Builder;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record WokRecipe(NamespacedKey key, Map<Integer, ItemStack> items,
                        Dish output, int requiredSeconds, int requiredStirs) implements Iterable<Map.Entry<Integer, ItemStack>>, PylonRecipe {
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

    @Override
    public @NotNull List<FluidOrItem> getInputs() {
        return items.values().stream().map(FluidOrItem::of).toList();
    }

    @Override
    public @NotNull List<FluidOrItem> getResults() {
        return List.of(FluidOrItem.of(output.getItem()));
    }

    @Override
    public @NotNull Gui display() {
        Gui.Builder.Normal builder = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # # 0 1 2 # # #",
                        "# b # 3 4 5 # r #",
                        "# # # 6 7 8 # # #",
                        "# # # # # # # # #"
                )
                .addIngredient('#', GuiItems.backgroundBlack())
                .addIngredient('b', ItemButton.fromStack(DelightItems.WOK))
                .addIngredient('r', ItemButton.fromStack(output.getItem()));

        ItemStack[] recipeItems = items.values().toArray(ItemStack[]::new);
        for (int i = 0; i < 9; i ++) {
            ItemStack recipeItem = recipeItems[i];
            builder.addIngredient(Integer.toString(i).charAt(0), recipeItem);
        }

        return builder.build();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }
}
