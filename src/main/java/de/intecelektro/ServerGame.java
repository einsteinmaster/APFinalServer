package de.intecelektro;

import io.swagger.model.Game;
import io.swagger.model.GeoCoord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class ServerGame {

    static class CapturePoint extends GeoCoord {
        static class TeamTimestamp {
            public int team;
            public long timestamp;

            public TeamTimestamp(int team, long timestamp) {
                this.team = team;
                this.timestamp = timestamp;
            }
        }

        List<TeamTimestamp> history = new ArrayList<>();
        int captureState = 0;

        void addHistory(int team, long timestamp) {
            history.add(new TeamTimestamp(team, timestamp));
        }

        boolean isInRange(GeoCoord coord2, double range) {
            double dalt = (this.getAltitude().doubleValue() - coord2.getAltitude().doubleValue());
            double dlat = (this.getLatitude().doubleValue() - coord2.getLatitude().doubleValue());
            double dlong = (this.getLongitude().doubleValue() - coord2.getLongitude().doubleValue());

            double distance = Math.sqrt(dalt * dalt + dlat * dlat + dlong * dlong);

            return distance < range;
        }
    }

    private static int id_counter = 1;
    private final int id;
    private long gameEnd;
    private final List<CapturePoint> capturePoints;
    public static final int TEAM_A = 1;
    public static final int TEAM_B = 2;
    private final Semaphore sem = new Semaphore(1, true);

    public ServerGame() {
        id = id_counter++;
        capturePoints = new ArrayList<>();
    }

    public String getId() {
        return "" + id;
    }

    public void playerAtGeoPoint(GeoCoord coord, int team) {
        int sign = -2 * team + 3;

        try {

            sem.acquire();

            CapturePoint c = capturePoints.stream().filter((e) -> e.isInRange(coord, 0.001)).findAny().get();

            int team_indep_capturestate = sign * c.captureState;
            if (team_indep_capturestate < 100) {
                if (team_indep_capturestate > 0)
                    c.captureState += sign;
                else
                    c.captureState += sign * 10;
                if (sign * c.captureState >= 100) {
                    c.addHistory(team, (new Date()).getTime());
                }
            }

            if(sign * c.captureState >= 100)
                this.capturePoints.stream().filter((e) -> e.isInRange(coord,0.00000000001))
                        .collect(Collectors.toList()).get(0).setTeam(team);

            sem.release();

        } catch (InterruptedException e) {
        }
    }

    public void updateData(Game game) {
        setCapturePoints(game.getCapturePoints());
        gameEnd = (new Date()).getTime() + game.getTimeLeft();
    }

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

    public List<GeoCoord> getCapturePoints() {
        return capturePoints.stream().map((e) -> (GeoCoord) e).collect(Collectors.toList());
    }

    public Integer getTimeLeft() {
        long timeLeft = gameEnd - (new Date()).getTime();
        return (int) timeLeft;
    }

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

    public Game getGame() {
        Game g = new Game();
        g.setId("" + id);
        g.setCapturePoints(getCapturePoints());
        g.setTimeLeft(getTimeLeft());
        g.setScore(getScore());
        return g;
    }
}
