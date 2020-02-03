package com.esiea.tp4A;

public class Point {
    public int posX;
    public int posY;
    public final int maxPosX;
    public final int minPosX;
    public final int maxPosY;
    public final int minPosY;


    public Point(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.maxPosX = 50;
        this.minPosX = -49;
        this.maxPosY = 50;
        this.minPosY = -49;
    }

    public int goForward() {
         return this.posX = (this.posX + 1) > maxPosX ? this.minPosX : this.posX + 1;
    }

    public int goBackward() {
        return this.posX = (this.posX -1) < minPosX ? this.maxPosX : this.posX - 1;
    }

    public int goLeft() {
        return this.posY = (this.posY - 1 ) < minPosY ? this.maxPosY : this.posY - 1;
    }

    public int goRight() {
        return this.posY = (this.posY + 1 ) > maxPosY ? this.minPosY : this.posY + 1;
    }

}
