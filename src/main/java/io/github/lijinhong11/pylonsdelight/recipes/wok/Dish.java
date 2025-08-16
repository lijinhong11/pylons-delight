package io.github.lijinhong11.pylonsdelight.recipes.wok;

import io.papermc.paper.datacomponent.item.FoodProperties;
import org.bukkit.potion.PotionEffect;

public record Dish(String dishId, float saturation, int nutrition, boolean canAlwaysEat, PotionEffect[] effects) {
    public Dish(String dishId, float saturation, int nutrition) {
        this(dishId, saturation, nutrition, false);
    }

    public Dish(String dishId, float saturation, int nutrition, boolean canAlwaysEat) {
        this(dishId, saturation, nutrition, canAlwaysEat, new PotionEffect[]{});
    }

    public FoodProperties toFoodComponent() {
        return FoodProperties.food()
                .nutrition(nutrition)
                .canAlwaysEat(canAlwaysEat)
                .saturation(saturation)
                .build();
    }
}
