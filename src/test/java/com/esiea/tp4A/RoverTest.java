package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    @ParameterizedTest
    @CsvSource({
        "0,0,N,0,1,N"
    })
    void is_rover_moved_right(int posX, int posY, Direction direction, int expectedPosX, int expectedPosY, Direction expectedDirection){
        //todo : try a way to use an array in CsvSource
        char [] commands = {'f'};
        Rover rover = new Rover(generatePosition(posX, posY, direction));
        rover.move(commands);
        assertThat(rover.position.point.posX).isEqualTo(expectedPosX);
        assertThat(rover.position.point.posY).isEqualTo(expectedPosY);
        assertThat(rover.position.direction).isEqualTo(expectedDirection);
    }

    private Position generatePosition(int posX, int posY, Direction direction) {
        Point point = new Point(posX, posY);
        return new Position(point, direction);
    }
}
