package com.esiea.tp4A;

import org.junit.Test;

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
}
