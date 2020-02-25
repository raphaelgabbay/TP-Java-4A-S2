package com.esiea.tp4A;

import com.esiea.tp4A.domain.MarsRover;

public class Rover implements MarsRover {
    public final PositionRover positionRover;
    public final Mars mars;

    public Rover(PositionRover positionRover, Mars mars) {
        this.positionRover = positionRover;
        this.mars = mars;
    }

    public PositionRover move(char[] commands){
        for (char command : commands) {
            executeCommand(command);
        }
        return positionRover;
    }

    private void executeCommand(char command) {
        switch(command) {
            case 'l': positionRover.rotateLeft(); break;
            case 'r': positionRover.rotateRight(); break;
            case 'f': positionRover.goForward(); break;
            case 'b': positionRover.goBackward(); break;
        }
    }
}
