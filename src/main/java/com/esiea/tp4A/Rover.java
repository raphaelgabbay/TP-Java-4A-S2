package com.esiea.tp4A;

public class Rover {
    public final Position position;
    public PlanetMap planetMap;

    public Rover(Position position,PlanetMap planetMap) {
        this.position = position;
        this.planetMap = planetMap;
    }

    public Position move(char[] commands){
        for (char command : commands) {
            executeCommand(command);
        }
        return position;
    }

    private void executeCommand(char command) {
        switch(command) {
            case 'l': position.rotateLeft(); break;
            case 'r': position.rotateRight(); break;
            case 'f': position.goForward(); break;
            case 'b': position.goBackward(); break;
        }
    }
}
