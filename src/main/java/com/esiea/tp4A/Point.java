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

    public void posXForward(){
        if(!map.checkIfObstacle(posX + 1,posY)) {
            posX = (posX + 1) > map.maxPosX ? map.minPosX : posX + 1;
        }
    }

    public void posXBackward(){
        if(!map.checkIfObstacle(posX - 1,posY)) {
            posX = (posX - 1) < map.minPosX ? map.maxPosX : posX - 1;
        }
    }
    public void posYForward(){
        if(!map.checkIfObstacle(posX ,posY + 1)) {
            posY = (posY + 1) > map.maxPosY ? map.minPosY : posY + 1;
        }
    }
    public void posYBackward(){
        if(!map.checkIfObstacle(posX ,posY - 1)) {
            posY = (posY - 1) < map.minPosY ? map.maxPosY : posY - 1;
        }
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
