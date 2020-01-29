package com.esiea.tp4A;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PointTest {



    @ParameterizedTest
    @CsvSource({
        "0,1",
        "1,2",
        "-49,-48",
        "50,-49"
    })
    void is_point_moved_forward(int a,int expectedResult) {
        Point p = new Point(a);
        p.forward();
        assertThat(p.pos).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0,-1",
        "-1,-2",
        "5,4",
        "-49,50"
    })
    void is_point_moved_backward(int a,int expectedResult) {
        Point p = new Point(a);
        p.backward();
        assertThat(p.pos).isEqualTo(expectedResult);
    }




}

