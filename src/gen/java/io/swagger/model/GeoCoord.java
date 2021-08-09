package io.swagger.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen", date = "2021-07-10T11:23:36.936Z[GMT]")public class GeoCoord   {
  private BigDecimal latitude = null;  private BigDecimal logitude = null;

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("latitude")
  public BigDecimal getLatitude() {
    return latitude;
  }
  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  /**
   **/
  
  @Schema(description = "")
  @JsonProperty("logitude")
  public BigDecimal getLogitude() {
    return logitude;
  }
  public void setLogitude(BigDecimal logitude) {
    this.logitude = logitude;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoCoord geoCoord = (GeoCoord) o;
    return Objects.equals(latitude, geoCoord.latitude) &&
        Objects.equals(logitude, geoCoord.logitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, logitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoCoord {\n");
    
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    logitude: ").append(toIndentedString(logitude)).append("\n");
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
