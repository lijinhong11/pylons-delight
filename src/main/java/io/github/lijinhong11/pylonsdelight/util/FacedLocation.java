package io.github.lijinhong11.pylonsdelight.util;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

/**
 * The class is used for correct the locations of the entities when player use different facing to place blocks.
 * @param base the base location
 * @param face player's facing
 */
public record FacedLocation(Location base, BlockFace face) {
    public FacedLocation(Location base, BlockFace face) {
        this.base = base.clone();
        this.face = simplify(face);
    }

    private BlockFace simplify(BlockFace face) {
        if (face == null) return BlockFace.NORTH;

        return switch (face) {
            case NORTH, SOUTH, EAST, WEST, UP, DOWN -> face;
            default -> {
                // 有 X/Z 分量 → 水平方向
                if (Math.abs(face.getModX()) > Math.abs(face.getModZ())) {
                    yield face.getModX() > 0 ? BlockFace.EAST : BlockFace.WEST;
                } else if (Math.abs(face.getModZ()) > 0) {
                    yield face.getModZ() > 0 ? BlockFace.SOUTH : BlockFace.NORTH;
                } else if (face.getModY() != 0) {
                    yield face.getModY() > 0 ? BlockFace.UP : BlockFace.DOWN;
                }
                yield BlockFace.NORTH;
            }
        };
    }

    public Location getRelative(double x, double y, double z) {
        double rx, ry, rz;

        switch (face) {
            case NORTH: // 面向 Z-
                rx = x;
                ry = y;
                rz = -z;
                break;
            case SOUTH: // 面向 Z+
                rx = -x;
                ry = y;
                rz = z;
                break;
            case EAST: // 面向 X+
                rx = z;
                ry = y;
                rz = x;
                break;
            case WEST: // 面向 X-
                rx = -z;
                ry = y;
                rz = -x;
                break;

            case UP: // Y+
            case DOWN: // Y-
                rx = x;
                ry = z;
                rz = y;
                break;

            default:
                rx = x;
                ry = y;
                rz = z;
                break;
        }

        return base.clone().add(rx, ry, rz);
    }

    public Location inFront(double distance) {
        return getRelative(0, 0, distance);
    }

    public Location behind(double distance) {
        return getRelative(0, 0, -distance);
    }

    public Location toRight(double distance) {
        return getRelative(distance, 0, 0);
    }

    public Location toLeft(double distance) {
        return getRelative(-distance, 0, 0);
    }

    public Location above(double distance) {
        return getRelative(0, distance, 0);
    }

    public Location below(double distance) {
        return getRelative(0, -distance, 0);
    }
}