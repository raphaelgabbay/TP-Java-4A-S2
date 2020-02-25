package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap {
    public final int maxPosX;
    public final int minPosX;
    public final int maxPosY;
    public final int minPosY;
    public Set<Position> obstacles;

    public Mars(int numberOfObstacles) {
        this.maxPosX = 50;
        this.minPosX = -49;
        this.maxPosY = 50;
        this.minPosY = -49;

        this.generateObstacles(numberOfObstacles);
    }

    public void generateObstacles(int number){
        this.obstacles = new HashSet <>();
        if(number < 0 ) return;
        while (obstacles.size() != number) {
            Position obstacle = generateUniqueRandomObstacle();
            obstacles.add(obstacle);
        }
    }

    private Position generateUniqueRandomObstacle(){
        Commons commons = new Commons();
        Point p = new Point(commons.getRandomInt(maxPosX,minPosX),commons.getRandomInt(maxPosY,minPosY),this);
        return new Position.FixedPosition(p.posX,p.posY,Direction.NORTH);
    }


    public boolean checkIfObstacle(int x, int y) {
        return obstacles.stream()
            .anyMatch(p -> p.getX() == x && p.getY() == y);
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }
}
