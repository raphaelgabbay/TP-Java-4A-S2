package com.esiea.tp4A;

public class Point {
    public int posX;
    public int posY;
    public final PlanetMap map;

    public Point(int posX, int posY, PlanetMap planetMap){
        this.posX = posX;
        this.posY = posY;
        this.map =  planetMap;
    }

    @Override
    public String toString() {
        return "Point{" +
            "posX=" + posX +
            ", posY=" + posY +
            ", map=" + map +
            '}';
    }
}
