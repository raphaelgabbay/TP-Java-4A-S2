package com.esiea.tp4A;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;



public class GameTest {
    public final Game game = Game.getGame();
    @Test
    void is_game_rightly_set() {
        assertThat(game.getMapSize() == 100 || game.getMapSize() == 300 || game.getMapSize() == 600);
        assertThat(game.getMars().getRovers().size() == 50);
        assertThat(game.getMars().obstaclePositions().size() == (int) (Math.pow(game.getMapSize(),2) * 0.15));
        assertThat(game.getLaserRange() == 5 || game.getLaserRange() == 30 || game.getLaserRange() == game.getMapSize() );
    }

    @ParameterizedTest
    @CsvSource({
        "1, 100",
        "2, 300",
        "3, 600"
    })
    void is_map_size_generated_right(int mapSize, int expectedMapSize) {
        assertThat(game.generateMapSize(mapSize)).isEqualTo(expectedMapSize);
    }

    @Test
    void is_laser_range_generated_right() {
        assertThat(game.generateLaserRange(1)).isEqualTo(5);
        assertThat(game.generateLaserRange(2)).isEqualTo(30);
        assertThat(game.generateLaserRange(3)).isEqualTo(game.getMapSize());
    }

}
