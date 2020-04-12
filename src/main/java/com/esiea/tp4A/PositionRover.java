package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;

public class PositionRover implements Position {
    private final Point point;
    private Direction direction;     //la direction ne peut pas être final car on on est obligé de la réaffecter quand elle est modifiée (car c'est un énum)

    public PositionRover(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }


    public Direction rotateLeft() {
        return direction = direction.rotateLeft();
    }

    public Direction rotateRight() {
        return direction = direction.rotateRight();
    }

    public Point goForward() {
        switch (direction) {
            case NORTH: point.posYForward(); break;
            case EAST: point.posXForward(); break;
            case SOUTH: point.posYBackward(); break;
            case WEST: point.posXBackward(); break;
        }
        return point;
    }

    public Point goBackward() {
        switch(direction) {
            case NORTH: point.posYBackward(); break;
            case EAST: point.posXBackward(); break;
            case SOUTH: point.posYForward(); break;
            case WEST: point.posXForward(); break;
        }
        return point;
    }

    @Override
    public int getX() {
        return point.getPosX();
    }

    @Override
    public int getY() {
        return point.getPosY();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
