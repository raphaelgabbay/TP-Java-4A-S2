package com.esiea.tp4A;

public class Position {
    public final Point point;
    public Direction direction;

    public Position(Point point, Direction direction) {
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
            case N : point.posYForward(); break;
            case E : point.posXForward(); break;
            case S : point.posYBackward(); break;
            case W : point.posXBackward(); break;
        }
        return point;
    }

    public Point goBackward() {
        switch(direction) {
            case N : point.posYBackward(); break;
            case E : point.posXBackward(); break;
            case S : point.posYForward(); break;
            case W : point.posXForward(); break;
        }
        return point;
    }
}
