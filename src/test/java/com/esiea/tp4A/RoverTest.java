package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoverTest {
    public final Mars mars = new Mars(0, 100);

    @ParameterizedTest
    @CsvSource(value = {
        "0,0,NORTH,fb,0,0,NORTH",
        "0,0,SOUTH,ffl,0,-2,EAST",
        "0,0,WEST,lll,0,0,NORTH",
        "0,-49,NORTH,bbl,0,49,WEST",
        "50,0,EAST,fl,-49,0,NORTH",
        "0,0,NORTH,rr,0,0,SOUTH"
    })
    void is_rover_moved_right(int posX, int posY, Direction direction, String stringCommands , int expectedPosX, int expectedPosY, Direction expectedDirection){

        Rover rover = new Rover(generatePosition(posX, posY,direction, mars), mars);
        rover.move(stringCommands);
        assertThat(rover.getPositionRover().getX()).isEqualTo(expectedPosX);
        assertThat(rover.getPositionRover().getY()).isEqualTo(expectedPosY);
        assertThat(rover.getPositionRover().getDirection()).isEqualTo(expectedDirection);
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
    void does_rover_respect_obstacles(int roverX, int roverY, Direction direction, int obsX, int obsY, String stringCommands, int expectedX, int expectedY, Direction expectedDirection){
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, mars), mars);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,Direction.NORTH));
        rover.move(stringCommands);
        assertThat(rover.getPositionRover().getX()).isEqualTo(expectedX);
        assertThat(rover.getPositionRover().getY()).isEqualTo(expectedY);
        assertThat(rover.getPositionRover().getDirection()).isEqualTo(expectedDirection);
    }
    @ParameterizedTest
    @CsvSource(value = {
        "0,0,NORTH,2,0,2,sff,0,2,NORTH",
        "0,50,NORTH,2,0,-49,sf,0,-49,NORTH",

        "0,0,SOUTH,2,0,-2,sff,0,-2,SOUTH",
        "0,-49,SOUTH,2,0,50,sf,0,50,SOUTH",

        "0,0,EAST,2,2,0,sff,2,0,EAST",
        "50,0,EAST,2,-49,0,sf,-49,0,EAST",

        "0,0,WEST,2,-2,0,sff,-2,0,WEST",
        "-49,0,WEST,2,50,0,sf,50,0,WEST"
    })
    void does_rover_shoot_and_move(int roverX,int roverY,Direction direction, int range, int obsX,int obsY,String stringCommands, int expectedX, int expectedY, Direction expectedDirection){

        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,Direction.NORTH));
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, mars), mars);
        rover.configureLaserRange(range);
        rover.move(stringCommands);
        assertThat(rover.getPositionRover().getX()).isEqualTo(expectedX);
        assertThat(rover.getPositionRover().getY()).isEqualTo(expectedY);
        assertThat(rover.getPositionRover().getDirection()).isEqualTo(expectedDirection);

    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,NORTH",
        "-1,2,SOUTH",
        "1,-2,EAST",
        "-1,-2,WEST"
    })
    void is_rover_intialized(int x, int y, Direction direction){
        Rover rover = new Rover(generatePosition(0, 0,direction, mars), mars);
        Position p = generatePosition(0, 0,direction, mars);
        rover.initialize(p);
        assertThat(rover.getPositionRover().getX() == x);
        assertThat(rover.getPositionRover().getY() == y);
        assertThat(rover.getPositionRover().getDirection() == direction);

    }
    @ParameterizedTest
    @CsvSource(value = {
        "5,100",
        "10,300",
        "15,600"
    })
    public void is_map_updated(int numberObstacles,int mapSize) {
        Mars m1 = new Mars(0, mapSize);
        Mars m2 = new Mars(numberObstacles, mapSize);
        Rover rover = new Rover(generatePosition(0, 0,Direction.NORTH, m1), m1);
        rover.updateMap(m2);
        assertThat(rover.getMars() == m2);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,100,0",
        "0,0,NORTH,99,1",
        "0,0,SOUTH,100,0",
        "0,0,SOUTH,99,1",
        "0,0,WEST,100,0",
        "0,0,WEST,99,1",
        "0,0,EAST,100,0",
        "0,0,EAST,99,1",
    })
    public void does_rover_destroy_itself(int roverX, int roverY, Direction direction, int range, int expectedSize) {
        Rover rover = new Rover(generatePosition(roverX, roverY, direction, mars), mars);
        rover.configureLaserRange(range);
        mars.addRover(rover);
        rover.shoot();
        assertThat(mars.getRovers().size()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @CsvSource({
        "'', 0, 0, NORTH",
        "f, 0, 1, NORTH",
        "b, 0, -1, NORTH",
        "l, 0, 0, WEST",
        "r, 0, 0, EAST",
        "lbblffr,2,-2,WEST",
        "aff,0,2,NORTH"
    })
    void basic_moves_from_center(String command, int expectedX, int expectedY, Direction expectedDirection) {
        MarsRover marsRover = new Rover(generatePosition(-1, 2, Direction.SOUTH, mars), mars);
        marsRover.initialize(Position.of(0, 0, Direction.NORTH));
        Position newPosition = marsRover.move(command);
        assertThat(newPosition)
            .as("new position after receiving command '" + command + "'")
            .isEqualTo(Position.of(expectedX, expectedY, expectedDirection));
    }

    private PositionRover generatePosition(int posX, int posY, Direction direction, Mars mars) {
        Point point = new Point(posX, posY, mars);
        return new PositionRover(point, direction);
    }





}
