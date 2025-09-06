package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.pylonmc.pylon.core.recipe.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.bukkit.inventory.recipe.CraftingBookCategory;

public class DelightRecipeSetup {
    private DelightRecipeSetup() {}

    public static void setup() {
        ShapedRecipe wok = new ShapedRecipe(DelightKeys.WOK, DelightItems.WOK)
                .shape("   ", "iii", " s ")
                .setIngredient('i', ItemStack.of(Material.IRON_INGOT))
                .setIngredient('s', ItemStack.of(Material.STICK));
        wok.setCategory(CraftingBookCategory.MISC);
        RecipeType.VANILLA_SHAPED.addRecipe(wok);

        WokRecipes.registerDefaultRecipe();

        ShapedRecipe raw_plate = new ShapedRecipe(DelightKeys.RAW_PLATE, DelightItems.RAW_PLATE)
                .shape("   ", "ttt", " t ")
                .setIngredient('t', ItemStack.of(Material.CLAY));
        raw_plate.setCategory(CraftingBookCategory.MISC);
        RecipeType.VANILLA_SHAPED.addRecipe(raw_plate);

        FurnaceRecipe plate = new FurnaceRecipe(DelightKeys.PLATE, DelightItems.PLATE, new RecipeChoice.ExactChoice(DelightItems.RAW_PLATE), 15f, 10);
        plate.setCategory(CookingBookCategory.MISC);
        RecipeType.VANILLA_FURNACE.addRecipe(plate);
    }
}
