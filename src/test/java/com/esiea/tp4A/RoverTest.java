package com.esiea.tp4A;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    private final Position position = new Position(1,1,Direction.E);
    private final Rover rover = new Rover(position);

    @ParameterizedTest
    @CsvSource({
        "1, 1, E",
        "4, 7, W"
    })
    void get_correctPosition(int a, int b, Direction d){
    }
}
