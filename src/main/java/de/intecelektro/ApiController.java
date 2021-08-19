package de.intecelektro;

import io.swagger.model.Game;
import io.swagger.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

/**
 * General entrypoint for any API-Call
 * Has a static global version who's methods are.
 */
public class ApiController {
    /**
     * Global api controller.
     */
    private static ApiController gController = null;

    /**
     * Returns the global controller and creates it if its not already created.
     * @return The global controller
     */
    public static ApiController getController(){
        if(gController == null)
            gController = new ApiController();
        return gController;
    }

    /**
     * API handler for the createGame method.
     * @param body startversion of the game.
     * @return The newly created game
     */
    public ResponseEntity<Game> handlerCreateGame(Game body) {
        IDataBase db = Database.GetDatabase();
        ServerGame g = db.createGame();
        body.setId(g.getId());
        db.updateGame(body);
        ServerGame g2 = db.getGame(g.getId());
        return new ResponseEntity<Game>(g2.getGame(), HttpStatus.OK);
    }

    /**
     * API handler for the getGame method.
     * @param gameId ID of the game to return.
     * @return The game with the ID gameID.
     */
    public ResponseEntity<Game> handlerGetGame(String gameId){
        try{
            Game g = Database.GetDatabase().getGame(gameId).getGame();
            return new ResponseEntity<Game>(g, HttpStatus.OK);
        }catch(NoSuchElementException exc){
            return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * API handler for the isAtCapturepoint method.
     * @param gameId ID of the game where the player is playing in.
     * @param player The player with its data (including where he is).
     * @return No data but an OK if everything went good.
     */
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
