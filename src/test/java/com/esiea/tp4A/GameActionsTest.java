package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GameActionsTest {
    public final Game game = Game.getGame();
    public final GameActions gameActions = new GameActions(game);

    @Test
    public void does_API_get_player_right() {
        game.getMars().getRovers().forEach(rover ->
                assertThat(rover.getPositionRover()).isEqualTo(gameActions.getPlayerPosition(rover.getId()))
            );
    }

    @Test
    public void is_player_added_right() {
        assertThat(gameActions.addPlayer("bob")).isEqualTo(true);
        assertThat(game.getPlayers().containsKey("bob")).isEqualTo(true);
        assertThat(gameActions.addPlayer("bob")).isEqualTo(false);
    }

    @Test
    public void is_maximum_respected() {
        for (int i = 0; i < 50; i++) {
            gameActions.addPlayer("bob"+i);
        }
        assertThat(gameActions.addPlayer("patrick")).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
        "1",
        "5",
        "10",
        "20"
    })
    public void are_obstacles_detected_right (int nbObstacles) {
        game.getMars().obstaclePositions().clear();
        game.getMars().getRovers().clear();
        for (int i = 1; i <= nbObstacles; i++) {
            //obstacles in sight
            game.getMars().obstaclePositions().add(new PositionRover(new Point(i,i, game.getMars()), Direction.NORTH));
            game.getMars().obstaclePositions().add(new PositionRover(new Point(-i,-i, game.getMars()), Direction.NORTH));
            //obstacles out of sight
            game.getMars().obstaclePositions().add(new PositionRover(new Point(i+30,i+30, game.getMars()), Direction.NORTH));
            game.getMars().obstaclePositions().add(new PositionRover(new Point(-i-30,-i-30, game.getMars()), Direction.NORTH));
        }
        game.getMars().addRover(new Rover(new PositionRover(new Point(0, 0, game.getMars()), Direction.NORTH), game.getMars(), 1));

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
        game.getMars().obstaclePositions().clear();
        game.getMars().getRovers().clear();
        for (int i = 1; i <= nbRovers; i++) {
            //obstacles in sight
            game.getMars().addRover(new Rover(new PositionRover(new Point(i, i, game.getMars()), Direction.NORTH), game.getMars(), i));
            game.getMars().addRover(new Rover(new PositionRover(new Point(-i, -i, game.getMars()), Direction.NORTH), game.getMars(), i));
            //obstacles out of sight
            game.getMars().addRover(new Rover(new PositionRover(new Point(i+30, i+30, game.getMars()), Direction.NORTH), game.getMars(), i));
            game.getMars().addRover(new Rover(new PositionRover(new Point(-i-30, -i-30, game.getMars()), Direction.NORTH), game.getMars(), i));
        }
        game.getMars().addRover(new Rover(new PositionRover(new Point(0, 0, game.getMars()), Direction.NORTH), game.getMars(), 0));

        assertThat(gameActions.getNearOpponentsPosition(0).size()).isEqualTo(nbRovers*2);
    }

    @Test
    public void is_ranged_get_right () {
        assertThat(game.getLaserRange()).isEqualTo(gameActions.getLaserRange());
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH,2,0,2",
        "0,0,SOUTH,2,0,-2",
        "0,0,EAST,2,2,0",
        "0,0,WEST,2,-2,0"
    })
    void does_rover_shoot_and_move(int roverX, int roverY, Direction direction, int range, int obsX, int obsY){
        game.getMars().obstaclePositions().clear();
        game.getMars().getRovers().clear();
        game.getMars().obstaclePositions().add(new Position.FixedPosition(obsX,obsY,Direction.NORTH));
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, game.getMars()), game.getMars(), 0);
        rover.configureLaserRange(range);
        game.getMars().addRover(rover);
        gameActions.roverShoot(0);
        assertThat(game.getMars().obstaclePositions().size() == 0);
    }
    @ParameterizedTest
    @CsvSource(value = {
        "0,0,NORTH,fb,0,0,NORTH",
        "0,0,SOUTH,ffl,0,-2,EAST",
        "0,0,WEST,lll,0,0,NORTH",
        "0,0,NORTH,rr,0,0,SOUTH"
    })
    void is_rover_moved_right(int roverX, int roverY, Direction direction, String stringCommands , int expectedPosX, int expectedPosY, Direction expectedDirection){
        game.getMars().obstaclePositions().clear();
        game.getMars().getRovers().clear();
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, game.getMars()), game.getMars(), 0);
        game.getMars().addRover(rover);

        gameActions.moveRover(0,stringCommands);

        assertThat(gameActions.getPlayerPosition(0).getX()).isEqualTo(expectedPosX);
        assertThat(gameActions.getPlayerPosition(0).getY()).isEqualTo(expectedPosY);
        assertThat(gameActions.getPlayerPosition(0).getDirection()).isEqualTo(expectedDirection);
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,NORTH",
        "0,0,SOUTH",
        "0,0,WEST",
        "0,0,EAST"
    })
    public void is_player_status_ok(int roverX, int roverY, Direction direction) {
        int range = game.getMapSize();
        game.getMars().obstaclePositions().clear();
        game.getMars().getRovers().clear();
        Rover rover = new Rover(generatePosition(roverX, roverY,direction, game.getMars()), game.getMars(), 0);
        rover.configureLaserRange(range);
        game.getMars().addRover(rover);
        gameActions.roverShoot(0);
        assertThat(gameActions.getRoverStatus(0));
    }

    @Test
    public void is_player_get_right() {
        gameActions.addPlayer("bob");
        assertThat(gameActions.getPlayerByName("bob")).isEqualTo(game.getPlayers().get("bob"));
        assertThat(gameActions.getPlayerByName("dylan")).isEqualTo(-1);
    }

    private PositionRover generatePosition(int posX, int posY, Direction direction, Mars mars) {
        Point point = new Point(posX, posY, mars);
        return new PositionRover(point, direction);
    }


}
