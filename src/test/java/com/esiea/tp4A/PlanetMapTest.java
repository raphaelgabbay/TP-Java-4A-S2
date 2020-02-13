package com.esiea.tp4A;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanetMapTest {
    @ParameterizedTest
    @CsvSource({
        "0,0",
        "3,3",
        "1,1",
        "-7,0"
    })
    void is_number_of_obstacles_correct(int numberOfObstacles,int expectedResult){
        PlanetMap pm = new PlanetMap(numberOfObstacles);
        assertThat(pm.obstacles.size()).isEqualTo(expectedResult);
    }




}
