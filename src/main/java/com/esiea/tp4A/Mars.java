package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap {
    private final MapCoordinates coordinates;
    private Set<Position> obstacles = new HashSet<>();
    private ArrayList<Rover> rovers = new ArrayList<>();

    public Mars(int numberOfObstacles, int mapSize) {
        coordinates = new MapCoordinates(mapSize/2, -1*((mapSize/2) - 1), mapSize/2, -1*((mapSize/2) - 1));
        this.generateObstacles(numberOfObstacles);
    }

    //todo : check there is no obstacle
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
        Point p;
        do {
            p = new Point(commons.getRandomInt(coordinates.getMaxPosX(), coordinates.getMinPosX()),
                commons.getRandomInt(coordinates.getMaxPosY(), coordinates.getMinPosY()),this);
        } while(checkIfObstacle(p.getPosX(), p.getPosY()));
        return new Position.FixedPosition(p.getPosX(),p.getPosY(),Direction.NORTH);
    }


    public boolean checkIfObstacle(int x, int y) {
        return obstacles.stream()
            .anyMatch(p -> p.getX() == x && p.getY() == y);
    }

    public void destroyObstacle(int x, int y) {
        obstacles.removeIf(position -> position.getX() == x && position.getY() == y);
    }

    public void generateRovers() {
        Commons commons = new Commons();
        for (int i = 0; i < 50 ; i++) {
            Point p = new Point(commons.getRandomInt(coordinates.getMaxPosX(), coordinates.getMinPosX())
                , commons.getRandomInt(coordinates.getMaxPosY(), coordinates.getMinPosY()), this);
            addRover(new Rover(
                new PositionRover(
                    p, Direction.NORTH), this));
        }
    }

    @Override
    public Set<Position> obstaclePositions() {
        return obstacles;
    }

    public MapCoordinates getCoordinates() {
        return coordinates;
    }

    public void setObstacles(Set<Position> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean addRover(Rover rover) {
        return rovers.add(rover);
    }

    public ArrayList<Rover> getRovers() {
        return rovers;
    }
}
