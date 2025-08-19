package io.github.lijinhong11.pylonsdelight.util;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;

public class ComponentUtils {
    private ComponentUtils() {}

    public static Component toTranslatableName(NamespacedKey key) {
        return Component.translatable("pylon." + key.getNamespace() + ".item." + key.getKey() + ".name");
    }

    public static Component getTranslatableMessage(String key) {
        return Component.translatable("pylon.pylons-delight.message." + key);
    }
}
