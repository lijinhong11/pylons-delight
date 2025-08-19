package io.github.lijinhong11.pylonsdelight.items.machines;

import io.github.lijinhong11.pylonsdelight.items.DelightItems;
import io.github.lijinhong11.pylonsdelight.recipes.WokRecipes;
import io.github.lijinhong11.pylonsdelight.objects.DelightDataKeys;
import io.github.lijinhong11.pylonsdelight.objects.DelightKeys;
import io.github.lijinhong11.pylonsdelight.util.ComponentUtils;
import io.github.pylonmc.pylon.base.entities.SimpleItemDisplay;
import io.github.pylonmc.pylon.core.block.PylonBlock;
import io.github.pylonmc.pylon.core.block.base.PylonEntityHolderBlock;
import io.github.pylonmc.pylon.core.block.base.PylonInteractableBlock;
import io.github.pylonmc.pylon.core.block.base.PylonTickingBlock;
import io.github.pylonmc.pylon.core.block.context.BlockCreateContext;
import io.github.pylonmc.pylon.core.entity.PylonEntity;
import io.github.pylonmc.pylon.core.entity.display.BlockDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.ItemDisplayBuilder;
import io.github.pylonmc.pylon.core.entity.display.transform.TransformBuilder;
import io.github.pylonmc.pylon.core.item.PylonItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.List;

public class Wok extends PylonBlock implements PylonEntityHolderBlock, PylonTickingBlock, PylonInteractableBlock {
    private final List<ItemStack> items = new ArrayList<>();

    private int ticks;
    private int stirs;

    public Wok(@NotNull Block block, @NotNull BlockCreateContext ctx) {
        super(block, ctx);

        BlockFace face = BlockFace.NORTH;
        if (ctx instanceof BlockCreateContext.PlayerPlace p) {
            face = p.getPlayer().getFacing();
        }
        setup(block, face);
    }

    public Wok(@NotNull Block block, @NotNull PersistentDataContainer pdc) {
        super(block, pdc);

        this.ticks = pdc.getOrDefault(DelightDataKeys.TICKS, PersistentDataType.INTEGER, 0);
        this.stirs = pdc.getOrDefault(DelightDataKeys.STIRS, PersistentDataType.INTEGER, 0);
    }

    private void setup(Block block, BlockFace face) {
        setTickInterval(20);

        Location loc = block.getLocation();

        addEntity("pan", createPan(loc));
        addEntity("stick", createStickEntity(loc, face));
    }

    @Override
    public void onInteract(@NotNull PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        ItemStack hand = inv.getItemInMainHand();
        World world = p.getWorld();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            PylonItem pylon = PylonItem.fromStack(hand);
            if (pylon != null) {
                if (pylon.getKey().getKey().startsWith("dish")) {
                    return;
                }
            }

            if (hand.isSimilar(DelightItems.KNIFE) || hand.isSimilar(DelightItems.CHOPPING_BOARD)) {
                return;
            }

            if (p.isSneaking() && hand.getType().isAir()) {
                for (ItemStack i : items) {
                    inv.addItem(i);
                }
                items.clear();
                return;
            }

            if (hand.isSimilar(DelightItems.PLATE)) {
                if (ticks == 0) {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-dish"));
                    e.setCancelled(true);
                    return;
                }
                
                if (WokRecipes.checkDone(items, ticks)) {
                    ItemStack result = WokRecipes.getResult(items);
                    if (result != null) {
                        int amount = hand.getAmount();
                        if (amount > 1) {
                            if (inv.firstEmpty() == -1) {
                                p.sendMessage(ComponentUtils.getTranslatableMessage("inv-full"));
                                return;
                            }

                            hand.setAmount(amount - 1);
                            inv.addItem(result);
                            e.setCancelled(true);
                        } else {
                            inv.setItemInMainHand(result);
                            e.setCancelled(true);
                        }
                    }
                    ticks = 0;
                    stirs = 0;
                    items.clear();
                    return;
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-stir-yet"));
                }
                return;
            }

            if (hand.isSimilar(DelightItems.SLICE)) {
                if (WokRecipes.findRecipe(items)) {
                    if (stirs == 0) {
                        stirs = 1;
                        p.sendMessage(ComponentUtils.getTranslatableMessage("wok.start-stir"));
                    } else {
                        stirs++;
                        p.sendMessage(ComponentUtils.getTranslatableMessage("wok.stir"));
                    }
                    return;
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.no-match"));
                }
                return;
            }

            if (!hand.getType().isAir()) {
                int slot = items.size();
                if (slot < 6) {
                    items.add(hand.clone().asOne());
                    hand.setAmount(hand.getAmount() - 1);
                    ticks = 1;
                    world.spawnParticle(Particle.SMOKE, getBlock().getLocation(), 5);
                } else {
                    p.sendMessage(ComponentUtils.getTranslatableMessage("wok.full"));
                }

                e.setCancelled(true);
            }
        }
    }

    @Override
    public void tick(double deltaSeconds) {
        if (ticks > 0) {
            if (WokRecipes.checkDone(items, ticks)) {
                ticks = Math.max(ticks, 1);
                return;
            }
            ticks++;
        }
    }

    @Override
    public void write(@NotNull PersistentDataContainer pdc) {
        super.write(pdc);
        pdc.set(DelightDataKeys.TICKS, PersistentDataType.INTEGER, ticks);
        pdc.set(DelightDataKeys.STIRS, PersistentDataType.INTEGER, stirs);
    }

    private Pan createPan(Location loc) {
        Location block = loc.toCenterLocation();
        block.subtract(0, 0.15, 0);
        return new Pan(new BlockDisplayBuilder()
                .material(Material.BLACK_CARPET)
                .brightness(1)
                .transformation(new TransformBuilder().scale(0.7))
                .build(block));
    }

    private SimpleItemDisplay createStickEntity(Location loc, BlockFace face) {
        Location block = loc.toCenterLocation();
        block.subtract(-0.001, 0.485, 0.5);

        Quaternionf quaternionf = getQuaternion(face);

        if (face.toString().startsWith("NORTH")) {
            block.add(0, 0, 1);
        }

        if (face.toString().startsWith("EAST")) {
            block.subtract(0.5, 0, -0.5);
        }

        if (face.toString().startsWith("WEST")) {
            block.subtract(-0.5, 0, -0.5);
        }

        return new SimpleItemDisplay(new ItemDisplayBuilder()
                .transformation(
                        new TransformBuilder()
                                .scale(0.45, 0.3, 0.45)
                                .rotate(quaternionf)
                )
                .itemStack(ItemStack.of(Material.STICK))
                .build(block));
    }

    private Quaternionf getQuaternion(BlockFace place) {
        Quaternionf base = new Quaternionf(0.659, 0.273, -0.268, 0.648);
        return switch (place) {
            case NORTH, NORTH_NORTH_EAST, NORTH_EAST, NORTH_WEST, NORTH_NORTH_WEST, SOUTH, DOWN, UP, SOUTH_EAST,
                 SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, SELF, SOUTH_WEST -> base;
            case EAST, EAST_NORTH_EAST, EAST_SOUTH_EAST -> base.rotateZ((float) Math.toRadians(-90));
            case WEST, WEST_NORTH_WEST, WEST_SOUTH_WEST -> base.rotateZ((float) Math.toRadians(90));
        };
    }

    public static class Pan extends PylonEntity<BlockDisplay> {
        public Pan(@NotNull BlockDisplay entity) {
            this(DelightKeys.ENTITY_PAN, entity);
        }

        public Pan(@NotNull NamespacedKey key, @NotNull BlockDisplay entity) {
            super(key, entity);
        }

        static {
            PylonEntity.register(DelightKeys.ENTITY_PAN, BlockDisplay.class, Pan.class);
        }
    }
}