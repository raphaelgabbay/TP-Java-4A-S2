package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap {
    private final int maxPosX;
    private final int minPosX;
    private final int maxPosY;
    private final int minPosY;
    private Set<Position> obstacles = new HashSet<>();

    public Mars(int numberOfObstacles) {
        this.maxPosX = 50;
        this.minPosX = -49;
        this.maxPosY = 50;
        this.minPosY = -49;

        this.generateObstacles(numberOfObstacles);
    }

    public void generateObstacles(int number) {
        if(number >= 0 ) {
            while (obstacles.size() != number) {
                Position obstacle = generateUniqueRandomObstacle();
                obstacles.add(obstacle);
            }
        }
    }

    private Position generateUniqueRandomObstacle(){
        Commons commons = new Commons();
        Point p = new Point(commons.getRandomInt(maxPosX,minPosX),commons.getRandomInt(maxPosY,minPosY),this);
        return new Position.FixedPosition(p.getPosX(),p.getPosY(),Direction.NORTH);
    }


    public boolean checkIfObstacle(int x, int y) {
        return obstacles.stream()
            .anyMatch(p -> p.getX() == x && p.getY() == y);
    }

    public void destroyObstacle(int x, int y) {
        obstacles.removeIf(position -> position.getX() == x && position.getY() == y);
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
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

    public void setObstacles(Set<Position> obstacles) {
        this.obstacles = obstacles;
    }
}
