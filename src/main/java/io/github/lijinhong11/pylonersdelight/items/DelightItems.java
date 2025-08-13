package io.github.lijinhong11.pylonersdelight.items;

import io.github.lijinhong11.pylonersdelight.util.DelightKeys;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class DelightItems {
    public static ItemStack WOK = ItemStackBuilder.pylonItem(Material.BLACK_CARPET, DelightKeys.WOK).build();

    public static void setup() {
        PylonItem.register(PylonItem.class, WOK);
    }
}
