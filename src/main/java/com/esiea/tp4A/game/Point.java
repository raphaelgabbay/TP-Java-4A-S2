package com.esiea.tp4A.game;

public class Point {
    private int posY;
    private int posX;
    private final Mars map;
    public Point(int posX, int posY, Mars mars){
        this.posX = posX; this.posY = posY; this.map = mars;
    }
    public boolean posXForward(){
        int posTest = getPosXForward();
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
            return true;
        }
        return false;
    }
    public boolean posXBackward(){
        int posTest = getPosXBackward();
        if(!map.checkIfObstacle(posTest,posY)) {
            posX = posTest;
            return true;
        }
        return false;
    }
    public boolean posYForward(){
        int posTest = getPosYForward();
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
            return true;
        }
        return false;
    }
    public boolean posYBackward(){
        int posTest = getPosYBackward();
        if(!map.checkIfObstacle(posX ,posTest)) {
            posY = posTest;
            return true;
        }
        return false;
    }
    public int getPosXForward(){
        return (posX + 1) > map.getCoordinates().getMaxPosX() ? map.getCoordinates().getMinPosX() : posX + 1;
    }
    public int getPosXBackward(){
        return (posX - 1) < map.getCoordinates().getMinPosX() ? map.getCoordinates().getMaxPosX() : posX - 1;
    }
    public int getPosYForward(){
        return (posY + 1) > map.getCoordinates().getMaxPosY() ? map.getCoordinates().getMinPosY() : posY + 1;
    }
    public int getPosYBackward(){
        return (posY - 1) < map.getCoordinates().getMinPosY() ? map.getCoordinates().getMaxPosY() : posY - 1;
    }
    public int getPosY() { return posY; }
    public int getPosX() { return posX; }
}
