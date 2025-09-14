package io.github.lijinhong11.pylonsdelight.recipes.general;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.recipes.subs.Chopping;

public class Choppings {
    private Choppings() {}

    public static final Chopping CUCUMBER_CUT = Chopping.builder()
            .key(DelightKeys.CUCUMBER)
            .input(DelightItems.CUCUMBER)
            .maxCuts(10)
            .output(DelightItems.CUCUMBER_CUT)
            .build();
}
