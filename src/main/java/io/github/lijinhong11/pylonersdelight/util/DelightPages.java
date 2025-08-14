package io.github.lijinhong11.pylonersdelight.util;

import io.github.lijinhong11.pylonersdelight.Delight;
import io.github.pylonmc.pylon.core.guide.pages.base.SimpleStaticGuidePage;
import org.bukkit.Material;

public class DelightPages {
    private DelightPages() {}

    public static SimpleStaticGuidePage MAIN;
    public static SimpleStaticGuidePage ITEMS;

    public static void setup() {
        MAIN = new SimpleStaticGuidePage(DelightKeys.PAGE, Delight.INSTANCE.getMaterial());
        ITEMS = new SimpleStaticGuidePage(DelightKeys.PAGE_ITEMS, Material.STICK);

        MAIN.addPage(ITEMS);
    }
}
