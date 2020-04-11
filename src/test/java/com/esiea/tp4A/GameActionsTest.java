package com.esiea.tp4A;

import com.esiea.tp4A.game.*;
import com.esiea.tp4A.game.domain.Direction;
import com.esiea.tp4A.game.domain.Position;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GameActionsTest {
    public final Game generalGame = new Game();
    public final GameActions gameActions = new GameActions(generalGame);

    @Test
    public void does_API_get_player_right () {
        generalGame.getMars().getRovers().forEach(rover ->
                assertThat(rover.getPositionRover()).isEqualTo(gameActions.getPlayerPosition(rover.getId()))
            );
    }

    @ParameterizedTest
    @CsvSource({
        "1",
        "5",
        "10",
        "20"
    })
    public void are_obstacles_detected_right (int nbObstacles) {
        generalGame.getMars().obstaclePositions().clear();
        generalGame.getMars().getRovers().clear();
        for (int i = 1; i <= nbObstacles; i++) {
            //obstacles in sight
            generalGame.getMars().obstaclePositions().add(new PositionRover(new Point(i,i, generalGame.getMars()), Direction.NORTH));
            generalGame.getMars().obstaclePositions().add(new PositionRover(new Point(-i,-i, generalGame.getMars()), Direction.NORTH));
            //obstacles out of sight
            generalGame.getMars().obstaclePositions().add(new PositionRover(new Point(i+30,i+30, generalGame.getMars()), Direction.NORTH));
            generalGame.getMars().obstaclePositions().add(new PositionRover(new Point(-i-30,-i-30, generalGame.getMars()), Direction.NORTH));
        }
        generalGame.getMars().addRover(new Rover(new PositionRover(new Point(0, 0, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), 1));

        assertThat(gameActions.getNearObstaclesPosition(1).size()).isEqualTo(nbObstacles*2);
    }

    @ParameterizedTest
    @CsvSource({
        "1",
        "5",
        "10",
        "20"
    })
    public void are_opponents_detected_right (int nbRovers) {
        generalGame.getMars().obstaclePositions().clear();
        generalGame.getMars().getRovers().clear();
        for (int i = 1; i <= nbRovers; i++) {
            //obstacles in sight
            generalGame.getMars().addRover(new Rover(new PositionRover(new Point(i, i, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), i));
            generalGame.getMars().addRover(new Rover(new PositionRover(new Point(-i, -i, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), i));
            //obstacles out of sight
            generalGame.getMars().addRover(new Rover(new PositionRover(new Point(i+30, i+30, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), i));
            generalGame.getMars().addRover(new Rover(new PositionRover(new Point(-i-30, -i-30, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), i));
        }
        generalGame.getMars().addRover(new Rover(new PositionRover(new Point(0, 0, generalGame.getMars()), Direction.NORTH), generalGame.getMars(), 0));

        assertThat(gameActions.getNearOpponentsPosition(0).size()).isEqualTo(nbRovers*2);
    }

    @Test
    public void is_ranged_get_right () {
        assertThat(generalGame.getLaserRange()).isEqualTo(gameActions.getLaserRange());
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,2,0,2",
        "0,0,SOUTH,2,0,-2",
        "0,0,EAST,2,2,0",
        "0,0,WEST,2,-2,0"
    })
    void does_rover_shoot_and_move(int roverX,int roverY,Direction direction, int range, int obsX,int obsY){
        generalGame.getMars().obstaclePositions().clear();
        generalGame.getMars().getRovers().clear();
        generalGame.getMars().obstaclePositions().add(new Position.FixedPosition(obsX,obsY,Direction.NORTH));
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, generalGame.getMars()), generalGame.getMars(), 0);
        rover.configureLaserRange(range);
        generalGame.getMars().addRover(rover);
        gameActions.roverShoot(0);
        assertThat(generalGame.getMars().obstaclePositions().size() == 0);
    }
    @ParameterizedTest
    @CsvSource(value = {
        "0,0,NORTH,fb,0,0,NORTH",
        "0,0,SOUTH,ffl,0,-2,EAST",
        "0,0,WEST,lll,0,0,NORTH",
        "0,-49,NORTH,bbl,0,49,WEST",
        "50,0,EAST,fl,-49,0,NORTH",
        "0,0,NORTH,rr,0,0,SOUTH"
    })
    void is_rover_moved_right(int roverX, int roverY, Direction direction, String stringCommands , int expectedPosX, int expectedPosY, Direction expectedDirection){
        generalGame.getMars().obstaclePositions().clear();
        generalGame.getMars().getRovers().clear();
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, generalGame.getMars()), generalGame.getMars(), 0);
        generalGame.getMars().addRover(rover);

        gameActions.moveRover(0,stringCommands);

        assertThat(gameActions.getPlayerPosition(0).getX()).isEqualTo(expectedPosX);
        assertThat(gameActions.getPlayerPosition(0).getY()).isEqualTo(expectedPosY);
        assertThat(gameActions.getPlayerPosition(0).getDirection()).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,100",
        "0,0,SOUTH,100",
        "0,0,WEST,100",
        "0,0,EAST,100"
    })
    public void is_player_status_ok(int roverX, int roverY, Direction direction, int range) {
        generalGame.getMars().obstaclePositions().clear();
        generalGame.getMars().getRovers().clear();
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, generalGame.getMars()), generalGame.getMars(), 0);
        rover.configureLaserRange(range);
        generalGame.getMars().addRover(rover);
        gameActions.roverShoot(0);
        assertThat(gameActions.getRoverStatus(0));
    }

    private PositionRover generatePosition(int posX, int posY, Direction direction, Mars mars) {
        Point point = new Point(posX, posY, mars);
        return new PositionRover(point, direction);
    }

}
