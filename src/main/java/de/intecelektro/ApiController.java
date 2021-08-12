package de.intecelektro;

import io.swagger.model.Game;
import io.swagger.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

public class ApiController {
    private static ApiController gController = null;
    public static ApiController getController(){
        if(gController == null)
            gController = new ApiController();
        return gController;
    }

    public ResponseEntity<Game> handlerCreateGame(Game body) {
        IDataBase db = Database.GetDatabase();
        ServerGame g = db.createGame();
        body.setId(g.getId());
        db.updateGame(body);
        ServerGame g2 = db.getGame(g.getId());
        return new ResponseEntity<Game>(g2.getGame(), HttpStatus.OK);
    }

    public ResponseEntity<Game> handlerGetGame(String gameId){
        try{
            Game g = Database.GetDatabase().getGame(gameId).getGame();
            return new ResponseEntity<Game>(g, HttpStatus.OK);
        }catch(NoSuchElementException exc){
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> handlerIsAtCapturepoint(String gameId, Player player){
        ServerGame game = (ServerGame) Database.GetDatabase().getGame(gameId);
        int team = player.getTeam();
        //try{
            game.playerAtGeoPoint(player.getPosition(),team);
            return new ResponseEntity<Void>(HttpStatus.OK);
        //}catch (NoSuchElementException exc){
            //return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        //}
    }
}
