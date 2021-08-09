package io.swagger.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.GeoCoord;
import java.util.List;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen", date = "2021-07-10T11:23:36.936Z[GMT]")public class Game   {
  private String id = null;  private Integer timeLeft = null;  private Integer score = null;  private List<GeoCoord> capturePoints = new ArrayList<GeoCoord>();

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("timeLeft")
  public Integer getTimeLeft() {
    return timeLeft;
  }
  public void setTimeLeft(Integer timeLeft) {
    this.timeLeft = timeLeft;
  }

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("score")
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("capturePoints")
  public List<GeoCoord> getCapturePoints() {
    return capturePoints;
  }
  public void setCapturePoints(List<GeoCoord> capturePoints) {
    this.capturePoints = capturePoints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(id, game.id) &&
        Objects.equals(timeLeft, game.timeLeft) &&
        Objects.equals(score, game.score) &&
        Objects.equals(capturePoints, game.capturePoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timeLeft, score, capturePoints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timeLeft: ").append(toIndentedString(timeLeft)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    capturePoints: ").append(toIndentedString(capturePoints)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
