package io.github.lijinhong11.pylonersdelight.recipes.wok;

import org.bukkit.potion.PotionEffect;

public record Dish(String dishId, int saturation, int foodLevel, PotionEffect[] effects) {
    public Dish(String dishId, int saturation, int foodLevel) {
        this(dishId, saturation, foodLevel, new PotionEffect[]{});
    }
}
