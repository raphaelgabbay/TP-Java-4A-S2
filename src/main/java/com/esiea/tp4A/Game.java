package com.esiea.tp4A;

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
        int ret = 1;
        switch(n){
            case 1 : ret = 100; break;
            case 2 : ret = 300; break;
            case 3 : ret = 600; break;
        }
        return ret;
    }

    public int generateLaserRange(int n) {
        int ret = 1;
        switch(n){
            case 1 : ret = 5; break;
            case 2 : ret = 30; break;
            case 3 : ret = mars.getCoordinates().getMaxPosX()*2; break; //this handle easily infinite range
        }
        return ret;
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
