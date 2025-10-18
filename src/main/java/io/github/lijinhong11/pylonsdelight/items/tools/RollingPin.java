package io.github.lijinhong11.pylonsdelight.items.tools;

import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.pylonmc.pylon.core.item.PylonItem;
import io.github.pylonmc.pylon.core.item.base.PylonInteractor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class RollingPin extends PylonItem implements PylonInteractor {
    private int rollingAmount;

    public RollingPin(@NotNull ItemStack stack) {
        super(stack);

        rollingAmount = stack.getPersistentDataContainer().getOrDefault(DelightDataKeys.ROLLS, PersistentDataType.INTEGER, 0);
    }

    @Override
    public void onUsedToRightClick(@NotNull PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack hand = e.getItem();
        if (EquipmentSlot.HAND != e.getHand()) {
            return;
        }

        if (Action.RIGHT_CLICK_BLOCK != e.getAction()) {
            return;
        }

        rollingAmount += 1;
        ItemMeta meta = hand.getItemMeta();
        meta.getPersistentDataContainer().set(DelightDataKeys.ROLLS, PersistentDataType.INTEGER, rollingAmount);
        hand.setItemMeta(meta);
    }
}
