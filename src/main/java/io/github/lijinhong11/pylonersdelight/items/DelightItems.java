package io.github.lijinhong11.pylonersdelight.items;

import io.github.lijinhong11.pylonersdelight.util.DelightKeys;
import io.github.lijinhong11.pylonersdelight.util.DelightPages;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DelightItems {
    public static ItemStack WOK = ItemStackBuilder.pylonItem(Material.BLACK_CARPET, DelightKeys.ITEM_WOK).build();
    public static ItemStack PLATE = ItemStackBuilder.pylonItem(Material.BOWL, DelightKeys.ITEM_PLATE).build();

    public static void setup() {
        PylonItem.register(PylonItem.class, WOK, DelightKeys.BLOCK_WOK);
        PylonItem.register(PylonItem.class, PLATE);

        DelightPages.ITEMS.addItem(DelightKeys.ITEM_WOK);
        DelightPages.ITEMS.addItem(DelightKeys.ITEM_PLATE);
    }
}
