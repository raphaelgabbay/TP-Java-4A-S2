package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    public final PlanetMap planetMap = new PlanetMap(10);

    @ParameterizedTest
    @CsvSource(value = {
        "0,0,N,fb,0,0,N",
        "0,0,S,ffl,0,-2,E",
        "0,0,W,lll,0,0,N",
        "0,-49,N,bbl,0,49,W",
        "50,0,E,fl,-49,0,N"
    })
    void is_rover_moved_right(int posX, int posY, Direction direction, String stringCommands ,int expectedPosX, int expectedPosY, Direction expectedDirection){

        Rover rover = new Rover(generatePosition(posX, posY,direction,planetMap),planetMap);
        char [] commands = stringCommands.toCharArray();
        rover.move(commands);
        assertThat(rover.position.point.posX).isEqualTo(expectedPosX);
        assertThat(rover.position.point.posY).isEqualTo(expectedPosY);
        assertThat(rover.position.direction).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0,50,N,0,-49,f,0,50,N",
        "0,50,S,0,49,f,0,50,S",
        "7,8,E,8,8,f,7,8,E",
        "0,0,W,-2,0,f,-1,0,W",

        "0,0,N,0,-1,b,0,0,N",
        "0,50,S,0,-49,b,0,50,S",
        "7,8,E,6,8,b,7,8,E",
        "0,0,W,1,0,b,0,0,W",

    })
    void is_rover_respect_obstacles(int roverX, int roverY, Direction direction,int obsX,int obsY,String stringCommands,int expectedX,int expectedY,Direction expectedDirection){
        Rover rover = new Rover(generatePosition(roverX, roverY,direction,planetMap),planetMap);
        char [] commands = stringCommands.toCharArray();
        planetMap.generateObstacles(0);
        planetMap.obstacles.add(new Obstacle(new Point(obsX,obsY,planetMap)));
        rover.move(commands);
        assertThat(rover.position.point.posX).isEqualTo(expectedX);
        assertThat(rover.position.point.posY).isEqualTo(expectedY);
        assertThat(rover.position.direction).isEqualTo(expectedDirection);
    }

    private Position generatePosition(int posX, int posY, Direction direction, PlanetMap planetMap) {
        Point point = new Point(posX, posY, planetMap);
        return new Position(point, direction);
    }


}
