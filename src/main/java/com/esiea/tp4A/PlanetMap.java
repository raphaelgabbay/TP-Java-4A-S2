package com.esiea.tp4A;

import java.util.ArrayList;
import java.util.Random;

public class PlanetMap {
    public final int maxPosX;
    public final int minPosX;
    public final int maxPosY;
    public final int minPosY;
    public ArrayList<Obstacle> obstacles;

    public PlanetMap(int numberOfObstacles) {
        this.maxPosX = 50;
        this.minPosX = -49;
        this.maxPosY = 50;
        this.minPosY = -49;

        this.generateObstacles(numberOfObstacles);
    }

    public void generateObstacles(int number){
        this.obstacles = new ArrayList<>();
        if(number < 0 ) return;
        while (obstacles.size() != number) {
            Obstacle obstacle = generateUniqueRandomObstacle();
            if(!obstacles.contains(obstacle)) obstacles.add(obstacle);
        }
    }

    private Obstacle generateUniqueRandomObstacle(){
        Point p = new Point(Commons.getRandomInt(maxPosX,minPosX),Commons.getRandomInt(maxPosY,minPosY),this);
        return new Obstacle(p);
    }


    public boolean checkIfObstacle(int x, int y) {
        return obstacles.stream()
            .anyMatch(o -> o.point.posX == x && o.point.posY == y);
    }
}
