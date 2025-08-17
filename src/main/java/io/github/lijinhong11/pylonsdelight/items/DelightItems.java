package io.github.lijinhong11.pylonsdelight.items;

import com.destroystokyo.paper.profile.ProfileProperty;
import io.github.lijinhong11.pylonsdelight.items.plants.sub.CucumberPlant;
import io.github.lijinhong11.pylonsdelight.items.plants.sub.TomatoPlant;
import io.github.lijinhong11.pylonsdelight.items.machines.Wok;
import io.github.lijinhong11.pylonsdelight.util.Constants;
import io.github.lijinhong11.pylonsdelight.util.DelightKeys;
import io.github.lijinhong11.pylonsdelight.util.DelightPages;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.FoodProperties;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DelightItems {
    public static ItemStack WOK = ItemStackBuilder.pylonItem(Material.STRUCTURE_VOID, DelightKeys.WOK)
            .set(DataComponentTypes.ITEM_MODEL, Material.BLACK_CARPET.getKey())
            .build();
    public static ItemStack PLATE = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.PLATE).build();

    public static ItemStack IMMATURE_TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.IMMATURE_TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllN2MyMTY3MzI4M2NlNjMwMDExY2M1MzgyNDYxMDg4MmNkZTNkOWE4YjYzMGMwZTIzMDU4ZTMxNDRkYiJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(2).saturation(0.3f).canAlwaysEat(false).build())
            .build();
    public static ItemStack TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiNzUyZTUyMzJiMDM5YjFlNzVlNDU0MTgzYTE5MmQ0MDU3YjdjYTgzMmY3YzI0YTVmZDg2Nzk2OWNiNGQifX19"))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(4).saturation(0.5f).canAlwaysEat(false).build())
            .build();
    public static ItemStack TOMATO_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.TOMATO_PLANT).build();

    public static ItemStack CUCUMBER = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.CUCUMBER)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdlNDFlNThkNjcwNDMzM2RkMmJiNTgwYmViMTBkNTcxMzgyMjY0ZTVmOGVhMmM5OTkwZmI1MDI0YTg0N2VlNiJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(3).saturation(0.45f).canAlwaysEat(true).build())
            .build();
    public static ItemStack CUCUMBER_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.CUCUMBER_PLANT).build();

    public static ItemStack FIRED_CHICKEN = ItemStackBuilder.pylonItem(Material.COOKED_CHICKEN, DelightKeys.FRIED_CHICKEN).build();

    public static void setup() {
        //items
        PylonItem.register(PylonItem.class, WOK, DelightKeys.WOK);
        PylonBlock.register(DelightKeys.WOK, Material.STRUCTURE_VOID, Wok.class);

        PylonItem.register(PylonItem.class, TOMATO_PLANT, DelightKeys.TOMATO_PLANT);
        PylonBlock.register(DelightKeys.TOMATO_PLANT, Material.OAK_SAPLING, TomatoPlant.class);

        PylonItem.register(PylonItem.class, IMMATURE_TOMATO, DelightKeys.IMMATURE_TOMATO);
        PylonBlock.register(DelightKeys.IMMATURE_TOMATO, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, TOMATO, DelightKeys.TOMATO);
        PylonBlock.register(DelightKeys.TOMATO, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, CUCUMBER_PLANT, DelightKeys.CUCUMBER_PLANT);
        PylonBlock.register(DelightKeys.CUCUMBER_PLANT, Material.OAK_SAPLING, CucumberPlant.class);

        PylonItem.register(PylonItem.class, CUCUMBER, DelightKeys.CUCUMBER);
        PylonBlock.register(DelightKeys.CUCUMBER, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, PLATE);

        //pages
        DelightPages.COOKERS.addItem(DelightKeys.WOK);
        DelightPages.COOKERS.addItem(DelightKeys.PLATE);

        DelightPages.PLANTS.addItem(DelightKeys.TOMATO_PLANT);

        DelightPages.FOOD.addItem(DelightKeys.IMMATURE_TOMATO);
        DelightPages.FOOD.addItem(DelightKeys.TOMATO);
    }
}
