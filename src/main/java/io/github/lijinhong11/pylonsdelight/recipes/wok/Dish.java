package io.github.lijinhong11.pylonsdelight.recipes.wok;

import io.github.lijinhong11.pylonsdelight.Delight;
import io.github.pylonmc.pylon.core.item.PylonItemSchema;
import io.github.pylonmc.pylon.core.registry.PylonRegistry;
import io.papermc.paper.datacomponent.item.FoodProperties;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

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

    public ItemStack getItem() {
        PylonItemSchema schema = PylonRegistry.ITEMS.get(new NamespacedKey(Delight.INSTANCE, "dish_" + dishId));
        return Objects.requireNonNull(schema).getItemStack();
    }
}
