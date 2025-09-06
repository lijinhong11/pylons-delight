package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.recipes.soda.SodaRecipe;
import io.github.pylonmc.pylon.core.item.builder.ItemStackBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.PotionContents;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SodaRecipes {
    private SodaRecipes() {}

    private static final ItemStack WATER_BOTTLE_STACK = ItemStackBuilder.of(Material.POTION)
            .set(DataComponentTypes.POTION_CONTENTS, PotionContents.potionContents().potion(PotionType.WATER).build())
            .build();

    public static final SodaRecipe BUBBLE_WATER = registerSodaRecipe(SodaRecipe.builder()
            .key(DelightKeys.BUBBLE_WATER)
            .input(WATER_BOTTLE_STACK.clone())
            .gasPercent(0.25f)
            .seconds(5)
            .output(DelightItems.BUBBLE_WATER)
            .build());

    public static SodaRecipe registerSodaRecipe(SodaRecipe soda) {
        sodas.put(soda.key(), soda);
        return soda;
    }

    @Nullable
    public static SodaRecipe getSodaRecipe(String id) {
        return sodas.get(id);
    }

    public static SodaRecipe findSodaRecipe(ItemStack input) {
        return sodas.values().stream().filter(c -> c.input().isSimilar(input)).findFirst().orElse(null);
    }
}
