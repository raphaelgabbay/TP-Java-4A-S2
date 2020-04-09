package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static com.esiea.tp4A.domain.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LaserTest {
    public final Mars mars = new Mars(10);
    Laser laser = new Laser(mars, 5);

    @ParameterizedTest
    @CsvSource({
        "0, 0, 2, 0, NORTH, EAST, True, True, False, True",
        "0, 0, 6, 0, NORTH, EAST, True, True, True, True",
        "0, 0, 2, 1, NORTH, EAST, True, True, True, True"
    })
    void is_laser_shot_right(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot, boolean IsObstacle, boolean IsObstacle2, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        Position pos = new Position.FixedPosition(roverX, roverY , NORTH);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX+1,obsY,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX+1, obsY)).isEqualTo(expectedIsObstacle2);

    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, -2, 0, NORTH, WEST, True, True, False, True",
        "0, 0, -6, 0, NORTH, WEST, True, True, True, True",
        "0, 0, -2, 1, NORTH, WEST, True, True, True, True"
    })
    void is_laser_shot_left(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot, boolean IsObastacle, boolean IsObastacle2, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        Position pos = new Position.FixedPosition(roverX, roverY , NORTH);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX-1,obsY,direction));
        //System.out.print(mars.checkIfObstacle(obsX, obsY));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX-1, obsY)).isEqualTo(expectedIsObstacle2);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 2, NORTH, NORTH, True, True, False, True",
        "0, 0, 0, 6, NORTH, NORTH, True, True, True, True",
        "0, 0, 1, 2, NORTH, NORTH, True, True, True, True",
    })
    void is_laser_shot_north(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot, boolean IsObastacle, boolean IsObastacle2, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        Position pos = new Position.FixedPosition(roverX, roverY , NORTH);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY+1,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX, obsY+1)).isEqualTo(expectedIsObstacle2);
    }


    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, -2, NORTH, SOUTH, True, True, False, True",
        "0, 0, 0, -6, NORTH, SOUTH, True, True, True, True",
        "0, 0, 1, -2, NORTH, SOUTH, True, True, True, True"
    })
    void is_laser_shot_south(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot, boolean IsObastacle, boolean IsObastacle2, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        Position pos = new Position.FixedPosition(roverX, roverY , NORTH);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY-1,direction));
        //System.out.print(mars.checkIfObstacle(obsX, obsY));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX, obsY-1)).isEqualTo(expectedIsObstacle2);

    }


}
