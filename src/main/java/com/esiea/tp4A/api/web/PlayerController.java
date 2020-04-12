package com.esiea.tp4A.api.web;

import com.esiea.tp4A.Game;
import com.esiea.tp4A.GameActions;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class PlayerController {
    private final Game game = Game.getGame();
    private final GameActions gameActions = new GameActions(game);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/player/{playerName}")
    public String authentication(@PathParam("playerName") String playerName) {
        return gameActions.addPlayer(playerName) + " " + playerName;
    }
}
