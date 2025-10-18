package io.github.lijinhong11.pylonsdelight.items.machines;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.pylonmc.pylon.base.BaseKeys;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.item.PylonItem;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class RollingTable extends PylonBlock implements PylonInteractBlock {
    public RollingTable(@NotNull Block block, @NotNull BlockCreateContext context) {
        super(block, context);
    }

    public RollingTable(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);
    }

    private int rollingAmount;
    private int needRollingAmount;
    private ItemStack dough;

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        if (EquipmentSlot.HAND != e.getHand()) {
            return;
        }

        if (Action.RIGHT_CLICK_BLOCK != e.getAction()) {
            return;
        }

        PylonItem item = PylonItem.fromStack(dough);
        if (item != null && item.getKey().equals(BaseKeys.DOUGH)) {
            if (null != e.getItem()) {
                if (null != e.getItem().getItemMeta()) {
                    ItemMeta meta = e.getItem().getItemMeta();
                    int num = meta.getPersistentDataContainer().get(DelightDataKeys.ROLLS, PersistentDataType.INTEGER);
                    if (needRollingAmount >= num) {
                        meta.getPersistentDataContainer().set(DelightDataKeys.ROLLS, PersistentDataType.INTEGER, rollingAmount);
                        e.getItem().setItemMeta(meta);
                    } else {

                    }
                }
            }
        }
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        pdc.set(DelightDataKeys.ITEM, DataType.ITEM_STACK, dough);
    }
}
