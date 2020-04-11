package com.esiea.tp4A.game;
import com.esiea.tp4A.game.domain.*;

public class Laser {
    private final Mars mars;
    private int range;

    public Laser(Mars mars, int range) {
        this.mars = mars;
        this.range = range;
    }

    //todo : détruire les rovers
    public void shoot(PositionRover pos, Direction dir){
        switch (dir) {
            case NORTH:
                shootNorth(pos); break;
            case SOUTH:
                shootSouth(pos); break;
            case EAST:
                shootEast(pos); break;
            case WEST:
                shootWest(pos); break;
        }
    }

    private void shootNorth(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posYForward()){
                this.mars.destroyObstacle(p.getPosX(),p.getPosYForward());
                break;
            }
        }
    }
    private void shootSouth(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posYBackward()){
                this.mars.destroyObstacle(p.getPosX(),p.getPosYBackward());
                break;
            }
        }
    }
    private void shootEast(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posXForward()){
                this.mars.destroyObstacle(p.getPosXForward(),p.getPosY());
                break;
            }
        }
    }
    private void shootWest(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posXBackward()){
                this.mars.destroyObstacle(p.getPosXBackward(),p.getPosY());
                break;
            }
        }
    }

    public void setRange(int range) {
        this.range = range;
    }
}
