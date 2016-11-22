package com.ollum.mazecape.Utils;

import com.ollum.mazecape.Classes.Tile;

public class TileUtils {
    public static Tile getTile(String currentTile) {
        Tile tile = new Tile();
        switch (currentTile.substring(0, 1)) {
            case "n":
                tile.setType(Tile.Type.NORMAL);
                break;
            case "g":
                tile.setType(Tile.Type.GOAL);
                break;
            case "s":
                tile.setType(Tile.Type.STAR);
                break;
            case "f":
                tile.setType(Tile.Type.FIRE);
                break;
            case "p":
                tile.setType(Tile.Type.PORTAL);
                break;
            case "m":
                tile.setType(Tile.Type.MONSTER);
                break;
            case "w":
                tile.setType(Tile.Type.SWORD);
                break;
            case "b":
                tile.setType(Tile.Type.BRIDGE);
                break;
            case "d":
                tile.setType(Tile.Type.DIARY);
                break;
            case "c":
                tile.setType(Tile.Type.CRACK);
                break;
            case "h":
                tile.setType(Tile.Type.HOLE);
                break;
            case "t":
                tile.setType(Tile.Type.TRAP_ACTIVE);
                break;
            case "i":
                tile.setType(Tile.Type.TRAP_INACTIVE);
                break;
            case "j":
                tile.setType(Tile.Type.JUMP);
                break;
            case "l":
                tile.setType(Tile.Type.LOOKOUT);
                break;
            case "o":
                tile.setType(Tile.Type.OCEAN);
                break;
            case "q":
                tile.setType(Tile.Type.BRIDGE_DESTROYED);
                break;
            case "r":
                tile.setType(Tile.Type.RIVER);
                break;
            case "v":
                tile.setType(Tile.Type.FOG);
                break;
            case "x":
                tile.setType(Tile.Type.BLOOD);
                break;
            case "y":
                tile.setType(Tile.Type.ROTATION);
                break;
            case "z":
                tile.setType(Tile.Type.SWITCH);
                break;
        }
        switch (currentTile.substring(1, 3)) {
            case "u":
                tile.setDirection(Tile.Direction.UP);
                break;
            case "d":
                tile.setDirection(Tile.Direction.DOWN);
                break;
            case "l":
                tile.setDirection(Tile.Direction.LEFT);
                break;
            case "r":
                tile.setDirection(Tile.Direction.RIGHT);
                break;
            case "ud":
                tile.setDirection(Tile.Direction.UP_DOWN);
                break;
            case "lr":
                tile.setDirection(Tile.Direction.LEFT_RIGHT);
                break;
            case "ul":
                tile.setDirection(Tile.Direction.UP_LEFT);
                break;
            case "ur":
                tile.setDirection(Tile.Direction.UP_RIGHT);
                break;
            case "dl":
                tile.setDirection(Tile.Direction.DOWN_LEFT);
                break;
            case "dr":
                tile.setDirection(Tile.Direction.DOWN_RIGHT);
                break;
            case "tu":
                tile.setDirection(Tile.Direction.T_UP);
                break;
            case "td":
                tile.setDirection(Tile.Direction.T_DOWN);
                break;
            case "tl":
                tile.setDirection(Tile.Direction.T_LEFT);
                break;
            case "tr":
                tile.setDirection(Tile.Direction.T_RIGHT);
                break;
            case "cs":
                tile.setDirection(Tile.Direction.CROSS);
                break;
        }
        return tile;
    }
}
