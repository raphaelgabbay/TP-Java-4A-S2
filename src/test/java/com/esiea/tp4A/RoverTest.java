package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    @ParameterizedTest
    @CsvSource(value = {
        "0,0,N,fb,0,0,N",
        "0,0,S,ffl,0,-2,E",
        "0,0,W,lll,0,0,N",
        "0,-49,N,bbl,0,49,W",
        "50,0,E,fl,-49,0,N"
    })
    void is_rover_moved_right(int posX, int posY, Direction direction, String stringCommands ,int expectedPosX, int expectedPosY, Direction expectedDirection){

        Rover rover = new Rover(generatePosition(posX, posY, direction));
        char [] commands = stringCommands.toCharArray();
        rover.move(commands);
        assertThat(rover.position.point.posX).isEqualTo(expectedPosX);
        assertThat(rover.position.point.posY).isEqualTo(expectedPosY);
        assertThat(rover.position.direction).isEqualTo(expectedDirection);a
    }

    private Position generatePosition(int posX, int posY, Direction direction) {
        Point point = new Point(posX, posY);
        return new Position(point, direction);
    }
}
