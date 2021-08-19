package de.intecelektro;

import io.swagger.model.Game;
import io.swagger.model.GeoCoord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * Representation of an Game-instance on the server.
 */
public class ServerGame {

    /**
     * Representation of an CapturePoint by the server.
     */
    static class CapturePoint extends GeoCoord {
        /**
         * Combination of Team (ID) and Timestamp
         */
        static class TeamTimestamp {
            /**
             * ID of a team. Can be 0 for Nothing, 1 for A and 2 for B
             */
            public int team;
            /**
             * Timestamp as milliseconds since 1970 from Date.getTime()
             */
            public long timestamp;
            /**
             * Constructs a TeamTimestamp-instance with the given values.
             * @param team ID of a team. Can be 0 for Nothing, 1 for A and 2 for B
             * @param timestamp Timestamp as milliseconds since 1970 from Date.getTime()
             */
            public TeamTimestamp(int team, long timestamp) {
                this.team = team;
                this.timestamp = timestamp;
            }
        }
        /**
         * Represents the history of the CapturePoint. It store the times and teams that once successfully captured this point.
         */
        List<TeamTimestamp> history = new ArrayList<>();
        /**
         * The state of the capturing process. 0 is neutral 100 is team 1(A) and -100 is team 2(B)
         */
        int captureState = 0;
        /**
         * Adds a point in the capturepoints history.
         * @param team ID of a team. Can be 0 for Nothing, 1 for A and 2 for B
         * @param timestamp Timestamp as milliseconds since 1970 from Date.getTime()
         */
        void addHistory(int team, long timestamp) {
            history.add(new TeamTimestamp(team, timestamp));
        }
        /**
         * Returns true if this and coord2 have less euclidian distance than range.
         * Takes altitude into account.
         * @param coord2 the other coord that should or should not be in range.
         * @param range the euclidian distance between them.
         * @return true if his and coord2 have less euclidian distance than range. Otherwise false.
         */
        boolean isInRange(GeoCoord coord2, double range) {
            double dalt = (this.getAltitude().doubleValue() - coord2.getAltitude().doubleValue());
            double dlat = (this.getLatitude().doubleValue() - coord2.getLatitude().doubleValue());
            double dlong = (this.getLongitude().doubleValue() - coord2.getLongitude().doubleValue());

            double distance = Math.sqrt(dalt * dalt + dlat * dlat + dlong * dlong);

            return distance < range;
        }
    }
    /**
     * Static counter where gameids are created from.
     */
    private static int id_counter = 1;
    /**
     * The ID of the Game. This is unique.
     */
    private final int id;
    /**
     * Timestamp where this Game is finished.
     */
    private long gameEnd;
    /**
     * A list of all capturepoints in this game.
     */
    private final List<CapturePoint> capturePoints;
    /**
     * integervalues of team A stored in Player.Team or TeamTimestamp.team
     */
    public static final int TEAM_A = 1;
    /**
     * integervalues of team B stored in Player.Team or TeamTimestamp.team
     */
    public static final int TEAM_B = 2;
    /**
     * Semaphore for multiple users calling isAtCaputurePoint
     */
    private final Semaphore sem = new Semaphore(1, true);
    /**
     * Creates an gameinstance with an unique ID
     */
    public ServerGame() {
        id = id_counter++;
        capturePoints = new ArrayList<>();
    }
    /**
     * Returns the GameID
     * @return GameID
     */
    public String getId() {
        return "" + id;
    }
    /**
     * True if c1, c2 are in range. false otherwise.
     * @param c1 first coord
     * @param c2 second coord
     * @param range range where they should be.
     * @return True if c1, c2 are in range. false otherwise.
     */
    public boolean isInRange(GeoCoord c1, GeoCoord c2, double range){
        double dlo = c1.getLongitude().doubleValue() - c2.getLongitude().doubleValue();
        double dla = c1.getLatitude().doubleValue() - c2.getLatitude().doubleValue();
        return dlo*dlo + dla*dla < range;
    }
    /**
     * Same method as above but with fixed range of 0.0000001
     * @param c1 first coord
     * @param c2 second coord
     * @return True if c1, c2 are in range. false otherwise.
     */
    public boolean isInRange(GeoCoord c1, GeoCoord c2){
        return isInRange(c1,c2,0.0000001);
    }
    /**
     * Updates the CapturePoint if a player from team is there.
     * @param coord Coordinate of the capturepoint.
     * @param team Team of the player who's there.
     */
    public void playerAtGeoPoint(GeoCoord coord, int team) {
        int sign = -2 * team + 3;

        //try {

            //sem.aquire();

            CapturePoint c = capturePoints.stream().filter((e) -> isInRange(e,coord)).findAny().get();

            int team_indep_capturestate = sign * c.captureState;
            if (team_indep_capturestate < 100) {
                if (team_indep_capturestate > 0)
                    c.captureState += sign * 20;
                else
                    c.captureState += sign * 40;
                if (sign * c.captureState >= 100) {
                    c.addHistory(team, (new Date()).getTime());
                }
            }

            if(sign * c.captureState >= 100)
                this.capturePoints.stream().filter((e) -> isInRange(e,coord))
                        .collect(Collectors.toList()).get(0).setTeam(team);

            //sem.release();

        //} catch (InterruptedException e) {
        //}
    }
    /**
     * Initializes the game's capturepoints and the gameEnd time to the given game instances's data.
     * @param game Game data to copy from.
     */
    public void updateData(Game game) {
        setCapturePoints(game.getCapturePoints());
        gameEnd = (new Date()).getTime() + game.getTimeLeft();
    }
    /**
     * Copies the capturepoints to a local version of them.
     * @param coords Capturepoints to copy.
     */
    public void setCapturePoints(List<GeoCoord> coords) {
        List<GeoCoord> coords_to_add = coords.stream().filter(
                (e) -> capturePoints.stream().noneMatch(
                        (e2) -> e2.equals(e)))
                .collect(Collectors.toList());
        for (GeoCoord c : coords_to_add) {
            CapturePoint capturePoint = new CapturePoint();
            capturePoint.setAltitude(c.getAltitude());
            capturePoint.setLatitude(c.getLatitude());
            capturePoint.setLongitude(c.getLongitude());
            capturePoint.setTeam(c.getTeam());
            capturePoints.add(capturePoint);
        }
    }
    /**
     * Returns a list of all capturepoints converted to GeoCoord instances.
     * @return List of capturepoints.
     */
    public List<GeoCoord> getCapturePoints() {
        return capturePoints.stream().map((e) -> (GeoCoord) e).collect(Collectors.toList());
    }
    /**
     * Returns the milliseconds until the game ends.
     * @return Milliseconds until game ends.
     */
    public Integer getTimeLeft() {
        long timeLeft = gameEnd - (new Date()).getTime();
        return (int) timeLeft;
    }
    /**
     * Returns the current score in the game.
     * @return Score of this game.
     */
    public Integer getScore() {
        double score_a = 0;
        double score_b = 0;
        final double TIME_TO_SCORE = 0.0001;
        long now = (new Date()).getTime();
        for (CapturePoint c : capturePoints) {
            List<CapturePoint.TeamTimestamp> entry = c.history;
            int last_team = -1;
            long last_time = -1;
            for (CapturePoint.TeamTimestamp p : entry) {
                if (last_team == TEAM_A) {
                    score_a += (p.timestamp - last_time) * TIME_TO_SCORE;
                } else if (last_team == TEAM_B) {
                    score_b += (p.timestamp - last_time) * TIME_TO_SCORE;
                }
                last_team = p.team;
                last_time = p.timestamp;
            }
            if (last_team == TEAM_A) {
                score_a += (now - last_time) * TIME_TO_SCORE;
            } else if (last_team == TEAM_B) {
                score_b += (now - last_time) * TIME_TO_SCORE;
            }
        }
        return (int) Math.round(score_a - score_b);
    }
    /**
     * Returns a new instance of this gameinstance as game.
     * @return New instance with this data as game.
     */
    public Game getGame() {
        Game g = new Game();
        g.setId("" + id);
        g.setCapturePoints(getCapturePoints());
        g.setTimeLeft(getTimeLeft());
        g.setScore(getScore());
        return g;
    }
}
