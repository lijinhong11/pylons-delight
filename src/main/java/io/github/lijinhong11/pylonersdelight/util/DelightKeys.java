package io.github.lijinhong11.pylonersdelight.util;

import io.github.lijinhong11.pylonersdelight.Delight;
import org.bukkit.NamespacedKey;

public class DelightKeys {
    private DelightKeys() {}

    public static final NamespacedKey PAGE = key("page");
    public static final NamespacedKey PAGE_ITEMS = key("page_items");

    public static final NamespacedKey ITEM_WOK = key("wok");
    public static final NamespacedKey ITEM_PLATE = key("plate");

    public static final NamespacedKey BLOCK_WOK = key("block_wok");

    public static final NamespacedKey ENTITY_PAN = key("entity_pan");

    public static final NamespacedKey DISH_TOMATO_WITH_EGGS = key("tomato_with_eggs");

    private static NamespacedKey key(String key) {
        return new NamespacedKey(Delight.INSTANCE, key);
    }
}
