package com.esiea.tp4A;

import com.esiea.tp4A.game.*;
import com.esiea.tp4A.game.domain.Direction;
import com.esiea.tp4A.game.domain.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.esiea.tp4A.game.domain.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LaserTest {
    public final Mars mars = new Mars(0, 100);
    public final Laser laser = new Laser(mars, 0);

    @ParameterizedTest
    @CsvSource({
        "0, 0, 2, 0, NORTH, EAST,5, False, True",
        "0, 0, 5, 0, NORTH, EAST,5, False, True",
        "0, 0, 6, 0, NORTH, EAST,5, True, True",
        "0, 0, 2, 1, NORTH, EAST,5, True, True"
    })
    void is_laser_shot_east(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot,int range, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        PositionRover pos = new PositionRover(new Point(roverX, roverY,mars), NORTH);
        laser.setRange(range);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX+1,obsY,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX+1, obsY)).isEqualTo(expectedIsObstacle2);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, -2, 0, NORTH, WEST,5, False, True",
        "0, 0, -5, 0, NORTH, WEST,5, False, True",
        "0, 0, -6, 0, NORTH, WEST,5, True, True",
        "0, 0, -2, 1, NORTH, WEST,5, True, True"
    })
    void is_laser_shot_west(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot,int range, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        PositionRover pos = new PositionRover(new Point(roverX, roverY,mars), NORTH);
        laser.setRange(range);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX-1,obsY,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX-1, obsY)).isEqualTo(expectedIsObstacle2);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 2, NORTH, NORTH,5, False, True",
        "0, 0, 0, 5, NORTH, NORTH,5, False, True",
        "0, 0, 0, 6, NORTH, NORTH,5, True, True",
        "0, 0, 1, 2, NORTH, NORTH,5, True, True",
    })
    void is_laser_shot_north(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot,int range, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        PositionRover pos = new PositionRover(new Point(roverX, roverY,mars), NORTH);
        laser.setRange(range);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY+1,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX, obsY+1)).isEqualTo(expectedIsObstacle2);
    }


    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, -2, NORTH, SOUTH,5, False, True",
        "0, 0, 0, -5, NORTH, SOUTH,5, False, True",
        "0, 0, 0, -6, NORTH, SOUTH,5, True, True",
        "0, 0, 1, -2, NORTH, SOUTH,5, True, True"
    })
    void is_laser_shot_south(int roverX, int roverY, int obsX, int obsY, Direction direction, Direction directionShoot,int range, boolean expectedIsObstacle, boolean expectedIsObstacle2){
        PositionRover pos = new PositionRover(new Point(roverX, roverY,mars), NORTH);
        laser.setRange(range);
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY,direction));
        mars.obstaclePositions().add(new Position.FixedPosition(obsX,obsY-1,direction));
        laser.shoot(pos, directionShoot);
        assertThat(mars.checkIfObstacle(obsX, obsY)).isEqualTo(expectedIsObstacle);
        assertThat(mars.checkIfObstacle(obsX, obsY-1)).isEqualTo(expectedIsObstacle2);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,0,1,NORTH,5, 0",
        "0,0,0,5,NORTH,5, 0",
        "0,0,0,6,NORTH,5, 1",
        "0,0,0,1,SOUTH,100,0",
    })
    void does_loser_destroy_rover(int rover1X, int rover1Y, int rover2X, int rover2Y, Direction direction, int range, int expectedSize) {
        PositionRover pos1 = new PositionRover(new Point(rover1X, rover1Y,mars), NORTH);
        PositionRover pos2 = new PositionRover(new Point(rover2X, rover2Y,mars), NORTH);
        mars.addRover(new Rover(pos2, mars));
        laser.setRange(range);
        laser.shoot(pos1, direction);
        assertThat(mars.getRovers().size()).isEqualTo(expectedSize);
    }




}
