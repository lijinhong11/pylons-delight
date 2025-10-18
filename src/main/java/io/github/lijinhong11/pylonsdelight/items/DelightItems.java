package io.github.lijinhong11.pylonsdelight.items;

import com.destroystokyo.paper.profile.ProfileProperty;
import io.github.lijinhong11.pylonsdelight.items.machines.ChoppingBoard;
import io.github.lijinhong11.pylonsdelight.items.machines.SodaMaker;
import io.github.lijinhong11.pylonsdelight.items.machines.Wok;
import io.github.lijinhong11.pylonsdelight.recipes.general.Dishes;
import io.github.lijinhong11.pylonsdelight.util.ComponentUtils;
import io.github.lijinhong11.pylonsdelight.util.Constants;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.objects.DelightPages;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.*;
import io.papermc.paper.datacomponent.item.consumable.ConsumeEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.List;

public class DelightItems {
    //cookers
    public static final ItemStack

    WOK = ItemStackBuilder.pylonItem(Material.STRUCTURE_VOID, DelightKeys.WOK)
            .set(DataComponentTypes.ITEM_MODEL, Material.BLACK_CARPET.getKey())
            .build(),

    RAW_PLATE = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.RAW_PLATE).build(),

    PLATE = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.PLATE).build(),
    SLICE = ItemStackBuilder.pylonItem(Material.STONE_SHOVEL, DelightKeys.SLICE).build(),
    KNIFE = ItemStackBuilder.pylonItem(Material.IRON_SWORD, DelightKeys.KNIFE).build(),
    CHOPPING_BOARD = ItemStackBuilder.pylonItem(Material.OAK_PRESSURE_PLATE, DelightKeys.CHOPPING_BOARD).build(),
    SODA_MAKER = ItemStackBuilder.pylonItem(Material.STRUCTURE_VOID, DelightKeys.SODA_MAKER)
            .set(DataComponentTypes.ITEM_MODEL, Material.WHITE_CONCRETE.getKey())
            .build(),

    //plants & food
    IMMATURE_TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.IMMATURE_TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllN2MyMTY3MzI4M2NlNjMwMDExY2M1MzgyNDYxMDg4MmNkZTNkOWE4YjYzMGMwZTIzMDU4ZTMxNDRkYiJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(3).saturation(0.3f).canAlwaysEat(false).build())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_SECONDS).addEffects(List.of(ConsumeEffect.applyStatusEffects(List.of(new PotionEffect(PotionEffectType.NAUSEA, 60, 1),new PotionEffect(PotionEffectType.POISON, 30, 2)), 1))).build())
            .name(ComponentUtils.toTranslatableName(DelightKeys.IMMATURE_TOMATO))
            .build(),

    TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiNzUyZTUyMzJiMDM5YjFlNzVlNDU0MTgzYTE5MmQ0MDU3YjdjYTgzMmY3YzI0YTVmZDg2Nzk2OWNiNGQifX19"))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(4).saturation(0.5f).canAlwaysEat(false).build())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_SECONDS).build())
            .name(ComponentUtils.toTranslatableName(DelightKeys.TOMATO))
            .build(),

    CUCUMBER = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.CUCUMBER)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdlNDFlNThkNjcwNDMzM2RkMmJiNTgwYmViMTBkNTcxMzgyMjY0ZTVmOGVhMmM5OTkwZmI1MDI0YTg0N2VlNiJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(8).saturation(0.375f).canAlwaysEat(true).build())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_SECONDS).build())
            .name(ComponentUtils.toTranslatableName(DelightKeys.CUCUMBER))
            .build(),
    CUCUMBER_CUT = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.CUCUMBER_CUT)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDYxZTc4ZmE1OTQyNzRiNGZmOTRhNjdlZTM5OTEzZmNlZGIxYzNhOGQ1ZjI2YzNlZGMzM2ZlZDQwMzI3MTA4MiJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(2).saturation(0.2f).canAlwaysEat(true).build())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_SECONDS).build())
            .name(ComponentUtils.toTranslatableName(DelightKeys.CUCUMBER_CUT))
            .build(),

    CABBAGE = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.CABBAGE)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRhZDY2MTU2MjhkNjUwNjNmNDdjZThjNjljOTIyNGIxZDkyMmNlN2MyZGQ5NTNmN2YxMmIxMTBkMDJiYmVmNSJ9fX0="))
                    .build())
            .set(DataComponentTypes.FOOD, FoodProperties.food().nutrition(7).saturation(0.4f).canAlwaysEat(true).build())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_SECONDS).build())
            .name(ComponentUtils.toTranslatableName(DelightKeys.CABBAGE))
            .build(),

    FRIED_CHICKEN = ItemStackBuilder.pylonItem(Material.COOKED_CHICKEN, DelightKeys.FRIED_CHICKEN).build(),

    //drinks
    BUBBLE_WATER = ItemStackBuilder.pylonItem(Material.POTION, DelightKeys.BUBBLE_WATER)
            .set(DataComponentTypes.POTION_CONTENTS, PotionContents.potionContents().potion(PotionType.WATER).build())
            .build(),

    //dishes
    DISH_TOMATO_WITH_EGGS = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.DISH_TOMATO_WITH_EGGS)
            .set(DataComponentTypes.FOOD, Dishes.TOMATO_WITH_EGGS.toFoodComponent())
            .set(DataComponentTypes.CONSUMABLE, Consumable.consumable().consumeSeconds(Constants.COMMON_EAT_DISH_SECONDS).build())
            .set(DataComponentTypes.USE_REMAINDER, UseRemainder.useRemainder(PLATE))
            .build()


            ;

    public static void setup() {
        //items
        PylonBlock.register(DelightKeys.WOK, Material.STRUCTURE_VOID, Wok.class);
        PylonBlock.register(DelightKeys.CHOPPING_BOARD, Material.OAK_PRESSURE_PLATE, ChoppingBoard.class);

        PylonItem.register(PylonItem.class, WOK, DelightKeys.WOK);
        PylonItem.register(PylonItem.class, CHOPPING_BOARD, DelightKeys.CHOPPING_BOARD);
        PylonItem.register(PylonItem.class, PLATE);
        PylonItem.register(PylonItem.class, SLICE);
        PylonItem.register(PylonItem.class, KNIFE);

        PylonItem.register(PylonItem.class, IMMATURE_TOMATO, DelightKeys.IMMATURE_TOMATO);
        PylonBlock.register(DelightKeys.IMMATURE_TOMATO, Material.PLAYER_HEAD, PylonBlock.class);
        PylonItem.register(PylonItem.class, TOMATO, DelightKeys.TOMATO);
        PylonBlock.register(DelightKeys.TOMATO, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, CUCUMBER, DelightKeys.CUCUMBER);
        PylonBlock.register(DelightKeys.CUCUMBER, Material.PLAYER_HEAD, PylonBlock.class);
        PylonItem.register(PylonItem.class, CUCUMBER_CUT);
        PylonBlock.register(DelightKeys.CUCUMBER_CUT, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, CABBAGE, DelightKeys.CABBAGE);
        PylonBlock.register(DelightKeys.CABBAGE, Material.PLAYER_HEAD, PylonBlock.class);

        PylonItem.register(PylonItem.class, SODA_MAKER, DelightKeys.SODA_MAKER);
        PylonBlock.register(DelightKeys.SODA_MAKER, Material.STRUCTURE_VOID, SodaMaker.class);

        PylonItem.register(PylonItem.class, FRIED_CHICKEN);

        //dishes' items
        PylonItem.register(PylonItem.class, DISH_TOMATO_WITH_EGGS);

        //drinks
        PylonItem.register(PylonItem.class, BUBBLE_WATER);

        //pages
        DelightPages.COOKERS.addItem(WOK);
        DelightPages.COOKERS.addItem(PLATE);
        DelightPages.COOKERS.addItem(SLICE);
        DelightPages.COOKERS.addItem(KNIFE);
        DelightPages.COOKERS.addItem(CHOPPING_BOARD);
        DelightPages.COOKERS.addItem(SODA_MAKER);

        DelightPages.PLANTS.addItem(DelightPlantItems.TOMATO_PLANT);
        DelightPages.PLANTS.addItem(DelightPlantItems.CUCUMBER_PLANT);
        DelightPages.PLANTS.addItem(DelightPlantItems.CABBAGE_PLANT);

        DelightPages.FOOD.addItem(IMMATURE_TOMATO);
        DelightPages.FOOD.addItem(TOMATO);
        DelightPages.FOOD.addItem(CUCUMBER);
        DelightPages.FOOD.addItem(CUCUMBER_CUT);
        DelightPages.FOOD.addItem(CABBAGE);
        DelightPages.FOOD.addItem(FRIED_CHICKEN);

        DelightPages.DRINKS.addItem(BUBBLE_WATER);
    }
}
