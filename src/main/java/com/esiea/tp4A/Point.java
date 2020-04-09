package com.esiea.tp4A;

public class Point {
    private int posY;
    private int posX;
    private final Mars map;

    public Point(int posX, int posY, Mars mars){
        this.posX = posX;
        this.posY = posY;
        this.map = mars;
    }

    public void posXForward(){
        int posTest = (posX + 1) > map.getCoordinates().getMaxPosX() ? map.getCoordinates().getMinPosX() : posX + 1;
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
        }
    }

    public void posXBackward(){
        int posTest = (posX - 1) < map.getCoordinates().getMinPosX() ? map.getCoordinates().getMaxPosX() : posX - 1;
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
        }
    }
    public void posYForward(){
        int posTest = (posY + 1) > map.getCoordinates().getMaxPosY() ? map.getCoordinates().getMinPosY() : posY + 1;
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
        }
    }
    public void posYBackward(){
        int posTest = (posY - 1) < map.getCoordinates().getMinPosY() ? map.getCoordinates().getMaxPosY() : posY - 1;
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
        }
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
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
