package com.esiea.tp4A.api.web;

import com.esiea.tp4A.Game;
import com.esiea.tp4A.GameActions;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class PlayerController {
    private final Game game = Game.getGame();
    private final GameActions gameActions = new GameActions(game);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/player/{playerName}")
    public Response authentication(@PathParam("playerName") String playerName) {
        return gameActions.addPlayer(playerName) ? Response.status(Response.Status.CREATED).build() : Response.status(Response.Status.CONFLICT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/player/{playerName}")
    public Response playerStatus(@PathParam("playerName") String playerName) {
        return gameActions.getPlayerByName(playerName) != -1 ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/player/{playerName}/{command}")
    public Response playerStatus(@PathParam("playerName") String playerName, @PathParam("command") String command) {
        if (gameActions.getPlayerByName(playerName) != -1) {
            gameActions.moveRover(gameActions.getPlayerByName(playerName), command);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
