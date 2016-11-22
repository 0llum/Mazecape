package com.ollum.mazecape.Classes;

public class Tile {
    private Direction direction;
    ;
    private Type type;
    ;

    public Tile(Direction direction, Type type) {
        this.direction = direction;
        this.type = type;
    }

    public Tile() {

    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        LEFT_RIGHT,
        UP_DOWN,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT,
        T_UP,
        T_DOWN,
        T_LEFT,
        T_RIGHT,
        CROSS
    }

    public enum Type {
        NORMAL,
        GOAL,
        STAR,
        FIRE,
        PORTAL,
        MONSTER,
        SWORD,
        CRACK,
        HOLE,
        TRAP_ACTIVE,
        TRAP_INACTIVE,
        FOG,
        LOOKOUT,
        SWITCH,
        ROTATION,
        BRIDGE,
        DIARY,
        JUMP,
        OCEAN,
        BRIDGE_DESTROYED,
        RIVER,
        BLOOD
    }
}
