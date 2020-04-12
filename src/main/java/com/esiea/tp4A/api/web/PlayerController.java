package com.esiea.tp4A.api.web;

import com.esiea.tp4A.Game;
import com.esiea.tp4A.GameActions;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        return gameActions.addPlayer(playerName) ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.CONFLICT).build();
    }
}
