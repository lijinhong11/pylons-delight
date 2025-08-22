package io.github.lijinhong11.pylonsdelight.items;

import io.github.lijinhong11.pylonsdelight.items.plants.sub.CabbagePlant;
import io.github.lijinhong11.pylonsdelight.items.plants.sub.CucumberPlant;
import io.github.lijinhong11.pylonsdelight.items.plants.sub.TomatoPlant;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DelightPlantItems {
    public static final ItemStack TOMATO_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.TOMATO_PLANT).build();
    public static final ItemStack CUCUMBER_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.CUCUMBER_PLANT).build();
    public static final ItemStack CABBAGE_PLANT = ItemStackBuilder.pylonItem(Material.OAK_SAPLING, DelightKeys.CABBAGE_PLANT).build();

    public static void setup() {
        PylonItem.register(PylonItem.class, DelightPlantItems.TOMATO_PLANT, DelightKeys.TOMATO_PLANT);
        PylonBlock.register(DelightKeys.TOMATO_PLANT, Material.OAK_SAPLING, TomatoPlant.class);

        PylonItem.register(PylonItem.class, DelightPlantItems.CUCUMBER_PLANT, DelightKeys.CUCUMBER_PLANT);
        PylonBlock.register(DelightKeys.CUCUMBER_PLANT, Material.OAK_SAPLING, CucumberPlant.class);

        PylonItem.register(PylonItem.class, DelightPlantItems.CABBAGE_PLANT, DelightKeys.CABBAGE_PLANT);
        PylonBlock.register(DelightKeys.CABBAGE_PLANT, Material.OAK_SAPLING, CabbagePlant.class);
    }
}
