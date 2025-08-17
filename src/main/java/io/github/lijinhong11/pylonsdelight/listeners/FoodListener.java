package io.github.lijinhong11.pylonsdelight.listeners;

import io.github.lijinhong11.pylonsdelight.util.DelightKeys;
import io.github.pylonmc.pylon.core.item.PylonItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FoodListener implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        ItemStack item = e.getItem();
        Player p = e.getPlayer();
        if (PylonItem.isPylonItem(item)) {
            PylonItem pylon = PylonItem.fromStack(item);
            if (DelightKeys.IMMATURE_TOMATO.equals(pylon.getKey())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 60, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 2));
            }
        }
    }
}
