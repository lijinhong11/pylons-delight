package io.github.lijinhong11.pylonsdelight.recipes.soda;

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

import java.util.List;

@Builder(toBuilder = true)
public record SodaRecipe(NamespacedKey key, ItemStack input, ItemStack output, float gasPercent, int seconds) implements PylonRecipe {
    @Override
    public @NotNull List<FluidOrItem> getInputs() {
        return List.of(FluidOrItem.of(input));
    }

    @Override
    public @NotNull List<FluidOrItem> getResults() {
        return List.of(FluidOrItem.of(output));
    }

    @Override
    public @NotNull Gui display() {
        Gui.Builder.Normal builder = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # # # # # # # #",
                        "# b # # 0 # # r #",
                        "# # # # # # # # #",
                        "# # # # # # # # #"
                )
                .addIngredient('#', GuiItems.backgroundBlack())
                .addIngredient('b', ItemButton.fromStack(DelightItems.SODA_MAKER))
                .addIngredient('0', ItemButton.fromStack(input))
                .addIngredient('r', ItemButton.fromStack(output));

        return builder.build();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }
}
