package com.esiea.tp4A;

import com.esiea.tp4A.game.Game;
import com.esiea.tp4A.game.GameActions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameActionsTest {
    public final Game generalGame = new Game();
    public final GameActions gameActions = new GameActions(generalGame);

    @Test
    public void does_API_get_player_right() {
        generalGame.getMars().getRovers().forEach(rover ->
                assertThat(rover.getPositionRover()).isEqualTo(gameActions.getPlayerPosition(rover.getId()))
            );
    }
}
