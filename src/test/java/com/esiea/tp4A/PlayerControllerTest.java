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

    @Test
    public void testPlayerStatusSuccess() {
        String uuid = UUID.randomUUID().toString();
        gameActions.addPlayer(uuid);
        given()
            .pathParam("playerName", uuid)
            .when().get("/api/player/{playerName}")
            .then()
            .statusCode(200);
    }

    @Test
    public void testPlayerStatusFailure() {
        String uuid = UUID.randomUUID().toString();
        given()
            .pathParam("playerName", uuid)
            .when().get("/api/player/{playerName}")
            .then()
            .statusCode(404);
    }

    @Test
    public void testPlayerActionsSuccess() {
        String uuid = UUID.randomUUID().toString();
        gameActions.addPlayer(uuid);
        given()
            .pathParam("playerName", uuid)
            .pathParam("command","f")
            .when().patch("/api/player/{playerName}/{command}")
            .then()
            .statusCode(200);
    }

    @Test
    public void testPlayerActionsFailure() {
        String uuid = UUID.randomUUID().toString();
        given()
            .pathParam("playerName", uuid)
            .pathParam("command","f")
            .when().patch("/api/player/{playerName}/{command}")
            .then()
            .statusCode(404);
    }

}
