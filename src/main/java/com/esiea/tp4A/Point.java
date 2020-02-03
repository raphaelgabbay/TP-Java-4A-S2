package com.esiea.tp4A;

public class Point {
    public int posX;
    public int posY;
    public PlanetMap map;

    public Point(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.map = new PlanetMap();
    }

    public int goForward() {
         return this.posX = (this.posX + 1) > map.maxPosX ? map.minPosX : this.posX + 1;
    }

    public int goBackward() {
        return this.posX = (this.posX -1) < map.minPosX ? map.maxPosX : this.posX - 1;
    }

    public int goLeft() {
        return this.posY = (this.posY - 1 ) < map.minPosY ? map.maxPosY : this.posY - 1;
    }

    public int goRight() {
        return this.posY = (this.posY + 1 ) > map.maxPosY ? map.minPosY : this.posY + 1;
    }

}
