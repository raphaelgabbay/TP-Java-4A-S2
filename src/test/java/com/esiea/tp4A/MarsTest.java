package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsTest {
    Mars pm = new Mars(0, 100);
    @ParameterizedTest
    @CsvSource({
        "0,0",
        "3,3",
        "1,1",
        "-7,0"
    })
    void is_number_of_obstacles_correct(int numberOfObstacles,int expectedResult){
        pm.generateObstacles(numberOfObstacles);
        assertThat(pm.obstaclePositions().size()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,0,0,true",
        "3,3,4,4,false",
        "-8,7,-8,-7,false",
        "-10,-10,-10,-10,true",
        "-4,7,-4,7,true"
    })
    void is_obstacle_in_given_position(int x,int y,int resX,int resY,boolean expected){
        pm.generateObstacles(0);
        pm.obstaclePositions().add(new Position.FixedPosition(x,y, Direction.NORTH));
        assertThat(pm.checkIfObstacle(resX,resY)).isEqualTo(expected);
    }


}
