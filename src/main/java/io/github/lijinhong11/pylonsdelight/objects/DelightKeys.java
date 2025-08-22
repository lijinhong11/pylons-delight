package io.github.lijinhong11.pylonsdelight.objects;

import io.github.lijinhong11.pylonsdelight.Delight;
import org.bukkit.NamespacedKey;

public class DelightKeys {
    private DelightKeys() {}

    //pages
    public static final NamespacedKey PAGE = key("page");
    public static final NamespacedKey PAGE_COOKERS = key("cookers");
    public static final NamespacedKey PAGE_FOOD = key("food");
    public static final NamespacedKey PAGE_PLANTS = key("plants");
    public static final NamespacedKey PAGE_DRINKS = key("drinks");
    public static final NamespacedKey PAGE_DISHES = key("dishes");

    //cookers & blocks
    public static final NamespacedKey WOK = key("wok");
    public static final NamespacedKey RAW_PLATE = key("raw_plate");
    public static final NamespacedKey PLATE = key("plate");
    public static final NamespacedKey SLICE = key("slice");
    public static final NamespacedKey KNIFE = key("knife");
    public static final NamespacedKey CHOPPING_BOARD = key("chopping_board");
    public static final NamespacedKey SODA_MAKER = key("soda_maker");

    //plants
    public static final NamespacedKey TOMATO_PLANT = key("tomato_plant");
    public static final NamespacedKey CUCUMBER_PLANT = key("cucumber_plant");
    public static final NamespacedKey CABBAGE_PLANT = key("cabbage_plant");

    //food
    public static final NamespacedKey IMMATURE_TOMATO = key("immature_tomato");
    public static final NamespacedKey TOMATO = key("tomato");
    public static final NamespacedKey CUCUMBER = key("cucumber");
    public static final NamespacedKey CUCUMBER_CUT = key("cucumber_cut");
    public static final NamespacedKey CABBAGE = key("cabbage");

    public static final NamespacedKey FRIED_CHICKEN = key("fried_chicken");

    //drinks
    public static final NamespacedKey BUBBLE_WATER = key("bubble_water");

    //entity
    public static final NamespacedKey ENTITY = key("entity");

    //dishes
    public static final NamespacedKey DISH_TOMATO_WITH_EGGS = key("dish_tomato_with_eggs");

    static NamespacedKey key(String key) {
        return new NamespacedKey(Delight.INSTANCE, key);
    }
}
