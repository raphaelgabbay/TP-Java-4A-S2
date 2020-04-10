package com.esiea.tp4A;
import com.esiea.tp4A.domain.*;

public class Laser {
    private final Mars mars;
    private int range;

    public Laser(Mars mars, int range) {
        this.mars = mars;
        this.range = range;
    }

    public void shoot(PositionRover pos, Direction dir){
        switch (dir) {
            case NORTH:shootN(pos); break;
            case SOUTH:shootS(pos); break;
            case EAST:shootE(pos); break;
            case WEST:shootW(pos); break;
        }
    }

    private void shootN(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posYForward()){
                this.mars.destroyObstacle(p.getPosX(),p.getPosYForward());
                break;
            }
        }
    }
    private void shootS(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posYBackward()){
                this.mars.destroyObstacle(p.getPosX(),p.getPosYBackward());
                break;
            }
        }
    }
    private void shootE(PositionRover pos) {
        Point p = new Point(pos.getX(),pos.getY(),mars);
        for(int i = 0 ; i < this.range ; i++){
            if(!p.posXForward()){
                this.mars.destroyObstacle(p.getPosXForward(),p.getPosY());
                break;
            }
        }
    }
    private void shootW(PositionRover pos) {
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
