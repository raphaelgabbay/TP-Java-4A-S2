package com.esiea.tp4A;


import static org.assertj.core.api.Assertions.assertThat;


import com.esiea.tp4A.domain.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionRoverTest {
    public final Mars mars = new Mars(10);

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,WEST",
        "0,0,WEST,SOUTH",
        "0,0,SOUTH,EAST",
        "0,0,EAST,NORTH"
    })
    void is_position_rotated_left(int posX, int posY, Direction direction, Direction expectedDirection){
        PositionRover positionRover = generatePosition(posX, posY, direction, mars);
        assertThat(positionRover.rotateLeft()).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,EAST",
        "0,0,EAST,SOUTH",
        "0,0,SOUTH,WEST",
        "0,0,WEST,NORTH"
    })
    void is_position_rotated_right(int posX, int posY, Direction direction, Direction expectedDirection){
        PositionRover positionRover = generatePosition(posX, posY, direction, mars);
        assertThat(positionRover.rotateRight()).isEqualTo(expectedDirection);
    }


    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,0,1",
        "0,50,NORTH,0,-49",
        "0,0,EAST,1,0",
        "50,0,EAST,-49,0",
        "0,0,SOUTH,0,-1",
        "0,-49,SOUTH,0,50",
        "0,0,WEST,-1,0",
        "-49,0,WEST,50,0"
    })
    void is_position_moved_forward_right(int posX, int posY, Direction direction, int expectedPosX, int expectedPosY){
        PositionRover positionRover = generatePosition(posX, posY, direction, mars);
        positionRover.goForward();
        assertThat(positionRover.getX()).isEqualTo(expectedPosX);
        assertThat(positionRover.getY()).isEqualTo(expectedPosY);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,0,-1",
        "0,-49,NORTH,0,50",
        "0,0,EAST,-1,0",
        "-49,0,EAST,50,0",
        "0,0,SOUTH,0,1",
        "0,0,WEST,1,0",
        "50,0,WEST,-49,0"
    })
    void is_position_moved_backward_right(int posX, int posY, Direction direction, int expectedPosX, int expectedPosY){
        PositionRover positionRover = generatePosition(posX, posY, direction, mars);
        positionRover.goBackward();
        assertThat(positionRover.getX()).isEqualTo(expectedPosX);
        assertThat(positionRover.getY()).isEqualTo(expectedPosY);
    }

    private PositionRover generatePosition(int posX, int posY, Direction direction, Mars mars) {
        Point point = new Point(posX, posY, mars);
        return new PositionRover(point, direction);
    }
}
