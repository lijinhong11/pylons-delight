package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.recipes.choppingboard.Chopping;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Choppings {
    private Choppings() {}

    private static final Map<String, Chopping> choppings = new ConcurrentHashMap<>();

    public static final Chopping CUCUMBER_CUT = registerChopping(Chopping.builder()
            .id("cucumber")
            .input(DelightItems.CUCUMBER)
            .maxCuts(10)
            .output(DelightItems.CUCUMBER_CUT)
            .build());

    public static Chopping registerChopping(Chopping chopping) {
        choppings.put(chopping.id(), chopping);
        return chopping;
    }

    @Nullable
    public static Chopping getChopping(String id) {
        return choppings.get(id);
    }

    public static Chopping findChopping(ItemStack input) {
        return choppings.values().stream().filter(c -> c.input().isSimilar(input)).findFirst().orElse(null);
    }
}
