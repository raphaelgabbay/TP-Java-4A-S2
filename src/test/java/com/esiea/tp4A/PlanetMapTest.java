package com.esiea.tp4A;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanetMapTest {
    PlanetMap pm = new PlanetMap(0);
    @ParameterizedTest
    @CsvSource({
        "0,0",
        "3,3",
        "1,1",
        "-7,0"
    })
    void is_number_of_obstacles_correct(int numberOfObstacles,int expectedResult){
        pm.generateObstacles(numberOfObstacles);
        assertThat(pm.obstacles.size()).isEqualTo(expectedResult);
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
        pm.obstacles.add(new Obstacle(new Point(x,y,pm)));
        assertThat(pm.checkIfObstacle(resX,resY)).isEqualTo(expected);
    }


}
