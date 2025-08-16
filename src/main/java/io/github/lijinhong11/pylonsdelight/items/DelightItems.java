package io.github.lijinhong11.pylonsdelight.items;

import com.destroystokyo.paper.profile.ProfileProperty;
import io.github.lijinhong11.pylonsdelight.items.plants.sub.TomatoPlant;
import io.github.lijinhong11.pylonsdelight.items.wok.Wok;
import io.github.lijinhong11.pylonsdelight.util.Constants;
import io.github.lijinhong11.pylonsdelight.util.DelightKeys;
import io.github.lijinhong11.pylonsdelight.util.DelightPages;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DelightItems {
    public static ItemStack WOK = ItemStackBuilder.pylonItem(Material.STRUCTURE_VOID, DelightKeys.ITEM_WOK)
            .set(DataComponentTypes.ITEM_MODEL, Material.BLACK_CARPET.getKey())
            .build();
    public static ItemStack PLATE = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.ITEM_PLATE).build();

    public static ItemStack IMMATURE_TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.ITEM_IMMATURE_TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("texture", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllN2MyMTY3MzI4M2NlNjMwMDExY2M1MzgyNDYxMDg4MmNkZTNkOWE4YjYzMGMwZTIzMDU4ZTMxNDRkYiJ9fX0="))
                    .build())
            .build();
    public static ItemStack TOMATO = ItemStackBuilder.pylonItem(Material.PLAYER_HEAD, DelightKeys.ITEM_TOMATO)
            .set(DataComponentTypes.PROFILE, ResolvableProfile
                    .resolvableProfile()
                    .name(Constants.HEAD)
                    .addProperty(new ProfileProperty("texture", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiNzUyZTUyMzJiMDM5YjFlNzVlNDU0MTgzYTE5MmQ0MDU3YjdjYTgzMmY3YzI0YTVmZDg2Nzk2OWNiNGQifX19"))
                    .build())
            .build();
    public static ItemStack TOMATO_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.ITEM_TOMATO_PLANT).build();

    public static void setup() {
        PylonBlock.register(DelightKeys.BLOCK_WOK, Material.STRUCTURE_VOID, Wok.class);
        PylonItem.register(PylonItem.class, WOK, DelightKeys.BLOCK_WOK);

        PylonBlock.register(DelightKeys.BLOCK_TOMATO_PLANT, Material.OAK_SAPLING, TomatoPlant.class);
        PylonItem.register(PylonItem.class, TOMATO_PLANT, DelightKeys.BLOCK_TOMATO_PLANT);
        PylonItem.register(PylonItem.class, TOMATO);

        PylonItem.register(PylonItem.class, PLATE);

        DelightPages.COOKERS.addItem(DelightKeys.ITEM_WOK);
        DelightPages.COOKERS.addItem(DelightKeys.ITEM_PLATE);
    }
}
