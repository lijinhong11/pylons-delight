package io.github.lijinhong11.pylonsdelight.recipes.choppingboard;

import lombok.Builder;
import org.bukkit.inventory.ItemStack;

@Builder
public record Chopping(String id, ItemStack input, ItemStack output, int maxCuts) {
}
