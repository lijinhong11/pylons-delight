package io.github.lijinhong11.pylonsdelight.objects;

import io.github.lijinhong11.pylonsdelight.Delight;
import io.github.pylonmc.pylon.core.content.guide.PylonGuide;
import io.github.pylonmc.pylon.core.guide.pages.base.SimpleStaticGuidePage;
import org.bukkit.Material;

public class DelightPages {
    private DelightPages() {}

    public static SimpleStaticGuidePage MAIN;
    public static SimpleStaticGuidePage COOKERS;
    public static SimpleStaticGuidePage PLANTS;
    public static SimpleStaticGuidePage FOOD;
    public static SimpleStaticGuidePage DISHES;
    public static SimpleStaticGuidePage DRINKS;

    public static void setup() {
        MAIN = new SimpleStaticGuidePage(DelightKeys.PAGE, Delight.INSTANCE.getMaterial());
        COOKERS = new SimpleStaticGuidePage(DelightKeys.PAGE_COOKERS, Material.STICK);
        FOOD = new SimpleStaticGuidePage(DelightKeys.PAGE_FOOD, Material.COOKED_PORKCHOP);
        PLANTS = new SimpleStaticGuidePage(DelightKeys.PAGE_PLANTS, Material.OAK_SAPLING);
        DRINKS = new SimpleStaticGuidePage(DelightKeys.PAGE_DRINKS, Material.GLASS_BOTTLE);
        DISHES = new SimpleStaticGuidePage(DelightKeys.PAGE_DISHES, Material.BOWL);

        MAIN.addPage(COOKERS);
        MAIN.addPage(PLANTS);
        MAIN.addPage(FOOD);
        MAIN.addPage(DISHES);
        MAIN.addPage(DRINKS);

        PylonGuide.getRootPage().addPage(MAIN);
    }
}
