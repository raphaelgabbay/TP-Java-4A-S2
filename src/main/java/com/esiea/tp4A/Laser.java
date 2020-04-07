package com.esiea.tp4A;
import com.esiea.tp4A.domain.*;

public class Laser {
    private final Mars mars;
    private int range;

    public Laser(Mars mars, int range) {
        this.mars = mars;
        this.range = range;
    }

    public void shoot(Position pos, Direction dir){
        switch (dir) {
            case NORTH:shootN(pos); break;
            case SOUTH:shootS(pos); break;
            case EAST:shootE(pos); break;
            case WEST:shootW(pos); break;
        }
    }

    private void shootN(Position pos) {
        for(int i = pos.getY(); i < pos.getY() + this.range; i++){
            if(this.mars.checkIfObstacle(pos.getX(), i)) {
                this.mars.destroyObstacle(pos.getX(), i);
            }
        }
    }
    private void shootS(Position pos) {
        for(int i = pos.getX(); i > pos.getX() - this.range; i--){
            if(this.mars.checkIfObstacle(pos.getX(), i)) {
                this.mars.destroyObstacle(pos.getX(), i);
            }
        }
    }
    private void shootE(Position pos) {
        for(int i = pos.getY(); i < pos.getY() + this.range; i++){
            if(this.mars.checkIfObstacle(pos.getY(), i)) {
                this.mars.destroyObstacle(pos.getY(), i);
            }
        }
    }
    private void shootW(Position pos) {
        for(int i = pos.getY(); i > pos.getY() - this.range; i--){
            if(this.mars.checkIfObstacle(pos.getY(), i)) {
                this.mars.destroyObstacle(pos.getY(), i);
            }
        }
    }

    public void setRange(int range) {
        this.range = range;
    }
}
