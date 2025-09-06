package io.github.lijinhong11.pylonsdelight.util;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.joml.Quaternionf;

public class EntityUtils {
    private EntityUtils() {}

    public static final Quaternionf BACK90_RIGHT45 = new Quaternionf(0.659, 0.273, -0.268, 0.648);

    public static Location moveBlockByFacing(Location loc, BlockFace face) {
        loc = loc.clone();
        if (face.toString().startsWith("SOUTH")) {
            loc.subtract(0, 0, 1);
        }

        if (face.toString().startsWith("EAST")) {
            loc.subtract(0.5, 0, 0.5);
        }

        if (face.toString().startsWith("WEST")) {
            loc.subtract(-0.5, 0, 0.5);
        }

        return loc;
    }

    public static Quaternionf getQuaternionForRotation(Quaternionf base, BlockFace place) {
        base = new Quaternionf(base);
        return switch (place) {
            case NORTH, NORTH_NORTH_EAST, NORTH_EAST, NORTH_WEST, NORTH_NORTH_WEST, SOUTH, DOWN, UP, SOUTH_EAST,
                 SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, SELF, SOUTH_WEST -> base;
            case EAST, EAST_NORTH_EAST, EAST_SOUTH_EAST -> base.rotateZ((float) Math.toRadians(-90));
            case WEST, WEST_NORTH_WEST, WEST_SOUTH_WEST -> base.rotateZ((float) Math.toRadians(90));
        };
    }
}
