package io.github.lijinhong11.pylonsdelight.recipes.choppingboard;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.util.ComponentUtils;
import io.github.pylonmc.pylon.core.guide.button.ItemButton;
import io.github.pylonmc.pylon.core.i18n.PylonArgument;
import io.github.pylonmc.pylon.core.recipe.FluidOrItem;
import io.github.pylonmc.pylon.core.recipe.PylonRecipe;
import io.github.pylonmc.pylon.core.util.gui.GuiItems;
import lombok.Builder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;

import java.util.List;

@Builder(toBuilder = true)
public record Chopping(NamespacedKey key, ItemStack input, ItemStack output, int maxCuts) implements PylonRecipe {
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
        ItemStack maxCuts = ItemStack.of(Material.IRON_SWORD, maxCuts());

        maxCuts.editMeta(m -> m.displayName(ComponentUtils.getTranslatableMessage("recipe_type.chopping_board.max_cuts", PylonArgument.of("%maxCuts%", maxCuts()))));

        Gui.Builder.Normal builder = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# b # # # # # # #",
                        "# # # # 0 # # r #",
                        "# t # # # # # # #",
                        "# # # # # # # # #"
                )
                .addIngredient('#', GuiItems.backgroundBlack())
                .addIngredient('b', ItemButton.fromStack(DelightItems.SODA_MAKER))
                .addIngredient('0', ItemButton.fromStack(input))
                .addIngredient('t', ItemButton.fromStack(maxCuts))
                .addIngredient('r', ItemButton.fromStack(output));

        return builder.build();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }
}
