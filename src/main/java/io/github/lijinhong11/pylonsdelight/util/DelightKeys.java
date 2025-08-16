package io.github.lijinhong11.pylonsdelight.util;

import io.github.lijinhong11.pylonsdelight.Delight;
import org.bukkit.NamespacedKey;

public class DelightKeys {
    private DelightKeys() {}

    public static final NamespacedKey PAGE = key("page");
    public static final NamespacedKey PAGE_COOKERS = key("cookers");
    public static final NamespacedKey PAGE_FOOD = key("food");
    public static final NamespacedKey PAGE_PLANTS = key("plants");

    public static final NamespacedKey ITEM_WOK = key("wok");
    public static final NamespacedKey ITEM_PLATE = key("plate");

    public static final NamespacedKey ITEM_IMMATURE_TOMATO = key("immature_tomato");
    public static final NamespacedKey ITEM_TOMATO = key("tomato");
    public static final NamespacedKey ITEM_TOMATO_PLANT = key("tomato_plant");

    public static final NamespacedKey BLOCK_WOK = key("block_wok");
    public static final NamespacedKey BLOCK_TOMATO_PLANT = key("block_tomato_plant");

    public static final NamespacedKey ENTITY_PAN = key("entity_pan");

    public static final NamespacedKey DISH_TOMATO_WITH_EGGS = key("tomato_with_eggs");

    static NamespacedKey key(String key) {
        return new NamespacedKey(Delight.INSTANCE, key);
    }
}
