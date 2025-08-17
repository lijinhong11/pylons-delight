package io.github.lijinhong11.pylonsdelight.objects;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import io.github.lijinhong11.pylonsdelight.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * The plant stage definition
 * @param percent the percent of the plant grows
 * @param itemStack the output item stack
 * @param edits the edit to the block
 */
public record PlantStage(float percent, ItemStack itemStack, Consumer<Block> edits) {
    public PlantStage(float percent, ItemStack itemStack, Consumer<Block> edits) {
        this.percent = percent;
        this.itemStack = itemStack;
        this.edits = edits;

        if (percent < 0) {
            throw new IllegalArgumentException("the percent must not less than 0");
        }

        if (percent > 100) {
            throw new IllegalArgumentException("the percent must not greater than 100");
        }
    }

    public PlantStage(float percent, ItemStack itemStack, String headBase64) {
        this(percent, itemStack, b -> {
            b.setType(Material.PLAYER_HEAD);
            Skull sk = (Skull) b.getState();
            PlayerProfile profile = Bukkit.createProfile(Constants.HEAD);
            profile.setProperty(new ProfileProperty("textures", headBase64));
            sk.setPlayerProfile(profile);
            sk.update(true);
        });
    }
}
