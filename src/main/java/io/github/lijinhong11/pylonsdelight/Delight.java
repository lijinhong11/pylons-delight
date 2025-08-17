package io.github.lijinhong11.pylonsdelight;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.listeners.FoodListener;
import io.github.lijinhong11.pylonsdelight.listeners.PlantListener;
import io.github.lijinhong11.pylonsdelight.util.DelightPages;
import io.github.pylonmc.pylon.core.addon.PylonAddon;
import org.bukkit.Bukkit;
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

        setupListeners();
    }

    private void setupListeners() {
        Bukkit.getPluginManager().registerEvents(new PlantListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Pylon's Delight disabled");
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
