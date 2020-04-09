package com.esiea.tp4A;

public class MapCoordinates {
    private final int maxPosX;
    private final int minPosX;
    private final int maxPosY;
    private final int minPosY;

    public MapCoordinates(int maxPosX, int minPosX, int maxPosY, int minPosY) {
        this.maxPosX = maxPosX;
        this.minPosX = minPosX;
        this.maxPosY = maxPosY;
        this.minPosY = minPosY;
    }

    public int getMaxPosX() {
        return maxPosX;
    }

    public int getMinPosX() {
        return minPosX;
    }

    public int getMaxPosY() {
        return maxPosY;
    }

    public int getMinPosY() {
        return minPosY;
    }
}
