package com.esiea.tp4A;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PointTest {



    @ParameterizedTest
    @CsvSource({
        "0,0,1",
        "50,0,-49"
    })
    void is_point_moved_forward(int posX, int posY,int expectedResult) {
        Point p = new Point(posX, posY);
        assertThat(p.goForward()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,-1",
        "-49,0,50"
    })
    void is_point_moved_backward(int posX, int posY,int expectedResult) {
        Point p = new Point(posX, posY);
        assertThat(p.goBackward()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,1",
        "0,50,-49"
    })
    void is_point_moved_right(int posX, int posY, int expectedResult){
        Point p = new Point(posX, posY);
        assertThat(p.goRight()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,-1",
        "0,-49,50"
    })
    void is_point_moved_left(int posX, int posY, int expectedResult){
        Point p = new Point(posX, posY);
        assertThat(p.goLeft()).isEqualTo(expectedResult);
    }


}

