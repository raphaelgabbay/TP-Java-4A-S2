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
            case N : point.posY = (point.posY + 1) > point.map.maxPosY ? point.map.minPosY : point.posY + 1; break;
            case E : point.posX = (point.posX + 1) > point.map.maxPosX ? point.map.minPosX : point.posX + 1; break;
            case S : point.posY = (point.posY - 1) < point.map.minPosY ? point.map.maxPosY : point.posY - 1; break;
            case W : point.posX = (point.posX - 1) < point.map.minPosX ? point.map.maxPosX : point.posX - 1; break;
        }
        return point;
    }

    public Point goBackward() {
        switch(direction) {
            case N : point.posY = (point.posY - 1) < point.map.minPosY ? point.map.maxPosY : point.posY - 1; break;
            case E : point.posX = (point.posX - 1) < point.map.minPosX ? point.map.maxPosX : point.posX - 1; break;
            case S : point.posY = (point.posY + 1) > point.map.maxPosY ? point.map.minPosY : point.posY + 1; break;
            case W : point.posX = (point.posX + 1) > point.map.maxPosX ? point.map.minPosX : point.posX + 1; break;
        }
        return point;
    }
}
