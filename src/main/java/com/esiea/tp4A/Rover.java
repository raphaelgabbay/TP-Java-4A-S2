package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;

public class Rover implements MarsRover {
    private PositionRover positionRover;
    private Mars mars;
    private Laser laser;

    public Rover(PositionRover positionRover, Mars mars) {
        this.positionRover = positionRover;
        this.mars = mars;
        this.laser = new Laser(mars,0);
    }

    @Override
    public Position move(String commands){
        for (char c : commands.toCharArray()) {
            executeCommand(c);
        }
        return positionRover;
    }

    private void executeCommand(char command) {
        switch(command) {
            case 'l': positionRover.rotateLeft(); break;
            case 'r': positionRover.rotateRight(); break;
            case 'f': positionRover.goForward(); break;
            case 'b': positionRover.goBackward(); break;
            case 's': shoot(); break;
        }
    }

    public PositionRover getPositionRover() {
        return positionRover;
    }

    public void shoot(){
        laser.shoot(positionRover,positionRover.getDirection());
    }

    @Override
    public MarsRover initialize(Position position) {
        Point point = new Point(position.getX(), position.getY(), mars);
        positionRover = new PositionRover(point, Direction.NORTH);
        return this;
    }

    @Override
    public MarsRover updateMap(PlanetMap map) {
        Mars mars = new Mars(0,100);
        mars.setObstacles(map.obstaclePositions());
        return this;
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        laser.setRange(range);
        return this;
    }
}
