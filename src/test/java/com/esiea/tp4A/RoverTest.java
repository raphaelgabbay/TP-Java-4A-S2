package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    public final Mars mars = new Mars(10);

    @ParameterizedTest
    @CsvSource(value = {
        "0,0,NORTH,fb,0,0,NORTH",
        "0,0,SOUTH,ffl,0,-2,EAST",
        "0,0,WEST,lll,0,0,NORTH",
        "0,-49,NORTH,bbl,0,49,WEST",
        "50,0,EAST,fl,-49,0,NORTH"
    })
    void is_rover_moved_right(int posX, int posY, Direction direction, String stringCommands , int expectedPosX, int expectedPosY, Direction expectedDirection){

        Rover rover = new Rover(generatePosition(posX, posY,direction, mars), mars);
        char [] commands = stringCommands.toCharArray();
        rover.move(commands);
        assertThat(rover.positionRover.point.posX).isEqualTo(expectedPosX);
        assertThat(rover.positionRover.point.posY).isEqualTo(expectedPosY);
        assertThat(rover.positionRover.direction).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0,50,NORTH,0,-49,f,0,50,NORTH",
        "0,50,SOUTH,0,49,f,0,50,SOUTH",
        "7,8,EAST,8,8,f,7,8,EAST",
        "0,0,WEST,-2,0,f,-1,0,WEST",

        "0,0,NORTH,0,-1,b,0,0,NORTH",
        "0,50,SOUTH,0,-49,b,0,50,SOUTH",
        "7,8,EAST,6,8,b,7,8,EAST",
        "0,0,WEST,1,0,b,0,0,WEST",

    })
    void is_rover_respect_obstacles(int roverX, int roverY, Direction direction,int obsX,int obsY,String stringCommands,int expectedX,int expectedY,Direction expectedDirection){
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, mars), mars);
        char [] commands = stringCommands.toCharArray();
        mars.generateObstacles(0);
        mars.obstacles.add(new Position.FixedPosition(obsX,obsY,Direction.NORTH));
        rover.move(commands);
        assertThat(rover.positionRover.point.posX).isEqualTo(expectedX);
        assertThat(rover.positionRover.point.posY).isEqualTo(expectedY);
        assertThat(rover.positionRover.direction).isEqualTo(expectedDirection);
    }

    private PositionRover generatePosition(int posX, int posY, Direction direction, Mars mars) {
        Point point = new Point(posX, posY, mars);
        return new PositionRover(point, direction);
    }


}
