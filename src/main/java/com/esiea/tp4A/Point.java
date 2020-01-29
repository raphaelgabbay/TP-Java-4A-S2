package com.esiea.tp4A;

public class Point {
    public int pos;
    public final int maxPos;
    public final int minPos;

    public Point(int pos){
        this.pos = pos;
        this.maxPos = 50;
        this.minPos = -49;
    }

    public void forward() {
        this.pos = (this.pos + 1) > maxPos ? this.minPos : this.pos + 1;
    }
    public void backward() {
        this.pos = (this.pos -1) < minPos ? this.maxPos : this.pos - 1;
    }


}
