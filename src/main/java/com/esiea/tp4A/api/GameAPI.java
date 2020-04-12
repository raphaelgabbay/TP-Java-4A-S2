package com.esiea.tp4A.api;

import com.esiea.tp4A.game.domain.Position;

import java.util.Set;

public interface GameAPI {
    Position getPlayerPosition(int playerId);
    Set<Position> getNearObstaclesPosition(int playerId);
    Set<Position> getNearOpponentsPosition(int playerId);
    int getLaserRange();
    void roverShoot(int playerId);
    void moveRover(int playerId, String commands);
    boolean getRoverStatus(int playerId);
    boolean addPlayer(String name);

}
