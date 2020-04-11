package com.esiea.tp4A.game;

public class Game {
    private final Mars mars;
    private final int mapSize;
    private final int laserRange;

    //todo : mettre en singleton
    public Game() {
        Commons commons = new Commons();
        int randomInt = commons.getRandomInt(3, 1);
        mapSize = generateMapSize(randomInt);
        mars = new Mars(0, mapSize);

        mars.generateObstacles((int) (Math.pow(mapSize,2) * 0.15));
        mars.generateRovers();
        laserRange = generateLaserRange(commons.getRandomInt(3, 1));
    }


    public int generateMapSize(int n) {
        switch(n){
            case 1 : return 100;
            case 2 : return 300;
            case 3 : return 600;
            default: return 100;
        }
    }

    public int generateLaserRange(int n) {
        switch(n){
            case 1 : return 5;
            case 2 : return 30;
            case 3 : return mars.getCoordinates().getMaxPosX()*2; //this handle easily infinite range
            default: return 5;
        }
    }

    public Mars getMars() {
        return mars;
    }

    public int getLaserRange() {
        return laserRange;
    }

    public int getMapSize() {
        return mapSize;
    }
}
