package io.github.lijinhong11.pylonersdelight;

import io.github.lijinhong11.pylonersdelight.items.DelightItems;
import io.github.lijinhong11.pylonersdelight.util.DelightKeys;
import io.github.lijinhong11.pylonersdelight.util.DelightPages;
import io.github.pylonmc.pylon.core.addon.PylonAddon;
import io.github.pylonmc.pylon.core.guide.pages.base.SimpleDynamicGuidePage;
import io.github.pylonmc.pylon.core.guide.pages.base.SimpleStaticGuidePage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Set;

public class Delight extends JavaPlugin implements PylonAddon {
    public static Delight INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        registerWithPylon();

        DelightPages.setup();
        DelightItems.setup();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public @NotNull JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public @NotNull Set<Locale> getLanguages() {
        return Set.of(Locale.ENGLISH, Locale.SIMPLIFIED_CHINESE, Locale.TRADITIONAL_CHINESE);
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.COOKED_BEEF;
    }
}
