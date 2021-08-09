package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.GeoCoord;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Game
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-09T14:50:32.719Z[GMT]")


public class Game   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("timeLeft")
  private Integer timeLeft = null;

  @JsonProperty("score")
  private Integer score = null;

  @JsonProperty("capturePoints")
  @Valid
  private List<GeoCoord> capturePoints = null;

  public Game id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Game timeLeft(Integer timeLeft) {
    this.timeLeft = timeLeft;
    return this;
  }

  /**
   * Get timeLeft
   * @return timeLeft
   **/
  @Schema(description = "")
  
    public Integer getTimeLeft() {
    return timeLeft;
  }

  public void setTimeLeft(Integer timeLeft) {
    this.timeLeft = timeLeft;
  }

  public Game score(Integer score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
   **/
  @Schema(description = "")
  
    public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Game capturePoints(List<GeoCoord> capturePoints) {
    this.capturePoints = capturePoints;
    return this;
  }

  public Game addCapturePointsItem(GeoCoord capturePointsItem) {
    if (this.capturePoints == null) {
      this.capturePoints = new ArrayList<GeoCoord>();
    }
    this.capturePoints.add(capturePointsItem);
    return this;
  }

  /**
   * Get capturePoints
   * @return capturePoints
   **/
  @Schema(description = "")
      @Valid
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
    return Objects.equals(this.id, game.id) &&
        Objects.equals(this.timeLeft, game.timeLeft) &&
        Objects.equals(this.score, game.score) &&
        Objects.equals(this.capturePoints, game.capturePoints);
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
