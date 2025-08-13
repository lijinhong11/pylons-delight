package io.github.lijinhong11.pylonersdelight.util;

import io.github.lijinhong11.pylonersdelight.Delight;
import org.bukkit.NamespacedKey;

public class DelightKeys {
    private DelightKeys() {}

    public static final NamespacedKey WOK = key("wok");
    public static final NamespacedKey PLATE = key("plate");

    public static final NamespacedKey DISH_TOMATO_WITH_EGGS = key("tomato_with_eggs");

    private static NamespacedKey key(String key) {
        return new NamespacedKey(Delight.INSTANCE, key);
    }
}
