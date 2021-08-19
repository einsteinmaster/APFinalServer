package de.intecelektro;

import io.swagger.model.Game;

/**
 * Normal database interface
 */
public interface IDataBase {
    ServerGame createGame();
    ServerGame updateGame(Game g);
    void deleteGame(String id);
    ServerGame getGame(String key);
}
