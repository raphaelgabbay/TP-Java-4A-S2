package com.esiea.tp4A;


import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {
    @ParameterizedTest
    @CsvSource({
        "0,0,N,W",
        "0,0,W,S",
        "0,0,S,E",
        "0,0,E,N"
    })
    void is_position_rotated_left(int posX, int posY, Direction direction, Direction expectedDirection){
        Position position = generatePosition(posX, posY, direction);
        assertThat(position.rotateLeft()).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,N,E",
        "0,0,E,S",
        "0,0,S,W",
        "0,0,W,N"
    })
    void is_position_rotated_right(int posX, int posY, Direction direction, Direction expectedDirection){
        Position position = generatePosition(posX, posY, direction);
        assertThat(position.rotateRight()).isEqualTo(expectedDirection);
    }


    @ParameterizedTest
    @CsvSource({
        "0,0,N,0,1",
        "0,50,N,0,-49",
        "0,0,E,1,0",
        "50,0,E,-49,0",
        "0,0,S,0,-1",
        "0,-49,S,0,50",
        "0,0,W,-1,0",
        "-49,0,W,50,0"
    })
    void is_position_moved_forward_right(int posX, int posY, Direction direction, int expectedPosX, int expectedPosY){
        Position position = generatePosition(posX, posY, direction);
        position.goForward();
        assertThat(position.point.posX).isEqualTo(expectedPosX);
        assertThat(position.point.posY).isEqualTo(expectedPosY);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,N,0,-1",
        "0,-49,N,0,50",
        "0,0,E,-1,0",
        "-49,0,E,50,0",
        "0,0,S,0,1",
        "0,0,W,1,0",
        "50,0,W,-49,0"
    })
    void is_position_moved_backward_right(int posX, int posY, Direction direction, int expectedPosX, int expectedPosY){
        Position position = generatePosition(posX, posY, direction);
        position.goBackward();
        assertThat(position.point.posX).isEqualTo(expectedPosX);
        assertThat(position.point.posY).isEqualTo(expectedPosY);
    }

    private Position generatePosition(int posX, int posY, Direction direction) {
        Point point = new Point(posX, posY);
        return new Position(point, direction);
    }
}
