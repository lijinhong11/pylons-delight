package io.github.lijinhong11.pylonsdelight.util;

import io.github.lijinhong11.pylonsdelight.Delight;
import io.github.pylonmc.pylon.core.content.guide.PylonGuide;
import io.github.pylonmc.pylon.core.guide.pages.base.SimpleStaticGuidePage;
import org.bukkit.Material;

public class DelightPages {
    private DelightPages() {}

    public static SimpleStaticGuidePage MAIN;
    public static SimpleStaticGuidePage COOKERS;
    public static SimpleStaticGuidePage FOOD;
    public static SimpleStaticGuidePage PLANTS;

    public static void setup() {
        MAIN = new SimpleStaticGuidePage(DelightKeys.PAGE, Delight.INSTANCE.getMaterial());
        COOKERS = new SimpleStaticGuidePage(DelightKeys.PAGE_COOKERS, Material.STICK);
        FOOD = new SimpleStaticGuidePage(DelightKeys.PAGE_FOOD, Material.PORKCHOP);
        PLANTS = new SimpleStaticGuidePage(DelightKeys.PAGE_PLANTS, Material.OAK_SAPLING);

        MAIN.addPage(COOKERS);

        PylonGuide.getRootPage().addPage(MAIN);
    }
}
