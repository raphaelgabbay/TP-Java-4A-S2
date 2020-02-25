package com.esiea.tp4A;

public class Point {
    public int posX;
    public int posY;
    public final Mars map;

    public Point(int posX, int posY, Mars mars){
        this.posX = posX;
        this.posY = posY;
        this.map = mars;
    }

    public void posXForward(){
        int posTest = (posX + 1) > map.maxPosX ? map.minPosX : posX + 1;
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
        }
    }

    public void posXBackward(){
        int posTest = (posX - 1) < map.minPosX ? map.maxPosX : posX - 1;
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
        }
    }
    public void posYForward(){
        int posTest = (posY + 1) > map.maxPosY ? map.minPosY : posY + 1;
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
        }
    }
    public void posYBackward(){
        int posTest = (posY - 1) < map.minPosY ? map.maxPosY : posY - 1;
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
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
