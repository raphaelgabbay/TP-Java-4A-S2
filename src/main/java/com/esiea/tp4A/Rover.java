package com.esiea.tp4A;

public class Rover {
    public final Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position move(char[] commands){
        for (char command : commands) {
            switch(command) {
                case 'l': position.rotateLeft(); break;
                case 'r': position.rotateRight(); break;
                case 'f': position.goForward(); break;
                case 'b': position.goBackward(); break;
            }
        }
        return position;
    }
}
