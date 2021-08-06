package de.intecelektro;

import io.swagger.model.Game;

import java.util.ArrayList;

public class Database implements IDataBase {
    private static Database gDataBase = null;
    public static IDataBase GetDatabase(){
        if(gDataBase == null)
            gDataBase = new Database();
        return gDataBase;
    }

    ArrayList<ServerGame> data = new ArrayList<>();

    @Override
    public ServerGame createGame() {
        ServerGame g = new ServerGame();
        data.add(g);
        return g;
    }

    @Override
    public ServerGame updateGame(Game g) {
        ServerGame game = data.stream().filter((e) -> e.getId().equals(g.getId())).findAny().get();
        game.updateData(g);
        return game;
    }

    @Override
    public void deleteGame(String key) {
        data.removeIf((e) -> e.getId().equals(key));
    }

    @Override
    public ServerGame getGame(String key) {
        return data.stream().filter((e) -> e.getId().equals(key)).findAny().get();
    }
}
