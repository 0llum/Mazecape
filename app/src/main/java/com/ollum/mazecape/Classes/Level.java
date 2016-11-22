package com.ollum.mazecape.Classes;

public class Level {
    private int width, height;
    private Scene scene;

    public Level(int width, int height, Scene scene) {
        this.width = width;
        this.height = height;
        this.scene = scene;
    }

    public enum Scene {
        FOREST,
        CAVE,
        SNOW,
        DESERT
    }
}
