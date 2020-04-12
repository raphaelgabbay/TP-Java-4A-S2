package com.esiea.tp4A;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PlayerControllerTest {
    private final Game game = Game.getGame();
    private final GameActions gameActions = new GameActions(game);

    @Test
    public void testAuthenticationSuccess() {
        String uuid = UUID.randomUUID().toString();
        given()
            .pathParam("playerName", uuid)
            .when().post("/api/player/{playerName}")
            .then()
            .statusCode(201);
    }

    @Test
    public void testAuthenticationFailure() {
        String uuid = UUID.randomUUID().toString();
        gameActions.addPlayer(uuid);
        given()
            .pathParam("playerName", uuid)
            .when().post("/api/player/{playerName}")
            .then()
            .statusCode(409);
    }
}
