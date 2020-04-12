package com.esiea.tp4A;

import com.esiea.tp4A.api.GameAPI;
import com.esiea.tp4A.domain.Position;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameActions implements GameAPI {
    private final Game game;

    public GameActions(Game game) {
        this.game = game;
    }

    @Override
    public Position getPlayerPosition(int playerId) {
        Optional<Rover> r = game.getMars().getRovers().stream().filter(rover -> rover.getId() == playerId).findFirst();
        return r.<Position>map(Rover::getPositionRover).orElse(null);
    }

    @Override
    public Set<Position> getNearObstaclesPosition(int playerId) {
        Position playerPosition = getPlayerPosition(playerId);
        return game.getMars().obstaclePositions().stream().filter(position -> position.getX() < playerPosition.getX() + 30 && position.getX() > playerPosition.getX() - 30 && position.getY() < playerPosition.getY() + 30 && position.getY() > playerPosition.getY() - 30).collect(Collectors.toSet());
    }

    @Override
    public Set<Position> getNearOpponentsPosition(int playerId) {
        Position playerPosition = getPlayerPosition(playerId);
        return game.getMars().getRovers().stream().filter(rover -> rover.getPositionRover().getX() <= playerPosition.getX() + 30 && rover.getPositionRover().getX() >= playerPosition.getX() - 30 && rover.getPositionRover().getY() <= playerPosition.getY() + 30 && rover.getPositionRover().getY() >= playerPosition.getY() - 30 && rover.getId() != playerId).collect(Collectors.toSet()).stream().map(Rover::getPositionRover).collect(Collectors.toSet());
    }

    @Override
    public int getLaserRange() {
        return game.getLaserRange();
    }

    @Override
    public void roverShoot(int playerId) {
        game.getMars().getRovers().stream().filter(rover -> rover.getId() == playerId).findFirst().ifPresent(Rover::shoot);
    }

    @Override
    public void moveRover(int playerId, String commands) {
        game.getMars().getRovers().stream().filter(rover -> rover.getId() == playerId).findFirst().ifPresent(rover -> rover.move(commands));
    }

    @Override
    public boolean getRoverStatus(int playerId) {
        return getPlayerPosition(playerId) != null;
    }

    @Override
    public boolean addPlayer(String name) {
        if (game.getPlayers().containsKey(name)) return false;
        Optional<Integer> first = game.getMars().getRovers().stream().map(Rover::getId).filter(integer -> !game.getPlayers().containsValue(integer)).findFirst();
        first.ifPresent(integer -> game.getPlayers().put(name, integer));
        return first.isPresent();
    }

    @Override
    public int getPlayerByName(String name) {
        return game.getPlayers().getOrDefault(name, -1);
    }
}
