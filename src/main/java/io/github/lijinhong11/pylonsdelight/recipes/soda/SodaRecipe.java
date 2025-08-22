package io.github.lijinhong11.pylonsdelight.recipes.soda;

import lombok.Builder;
import org.bukkit.inventory.ItemStack;

@Builder
public record SodaRecipe(String id, ItemStack input, ItemStack output, float gasPercent, int seconds) {
}
