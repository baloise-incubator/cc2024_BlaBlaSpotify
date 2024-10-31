package ch.srgssr.epg.api;

import java.net.URI;
import java.util.Objects;
import ch.srgssr.epg.api.ProgramDateTimes;
import ch.srgssr.epg.api.ProgramImage;
import ch.srgssr.epg.api.ProgramPeopleInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Program
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-31T10:50:32.787731300+01:00[Europe/Zurich]")
public class Program {

  @JsonProperty("title")
  private String title;

  @JsonProperty("shortDescription")
  private String shortDescription;

  @JsonProperty("longDescription")
  private String longDescription;

  @JsonProperty("dateTimes")
  private ProgramDateTimes dateTimes;

  @JsonProperty("adultWarning")
  private Boolean adultWarning;

  @JsonProperty("isLive")
  private Boolean isLive;

  @JsonProperty("primeur")
  private Boolean primeur;

  @JsonProperty("hasSubtitle")
  private Boolean hasSubtitle;

  @JsonProperty("replica")
  private Boolean replica;

  @JsonProperty("geoBlocked")
  private Boolean geoBlocked;

  @JsonProperty("longHtmlDesciption")
  private String longHtmlDesciption;

  @JsonProperty("productionYear")
  private String productionYear;

  @JsonProperty("episodeNumber")
  private String episodeNumber;

  @JsonProperty("people")
  @Valid
  private List<ProgramPeopleInner> people = null;

  @JsonProperty("image")
  private ProgramImage image;

  public Program title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", required = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Program shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

  /**
   * Get shortDescription
   * @return shortDescription
  */
  
  @Schema(name = "shortDescription", required = false)
  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public Program longDescription(String longDescription) {
    this.longDescription = longDescription;
    return this;
  }

  /**
   * Get longDescription
   * @return longDescription
  */
  
  @Schema(name = "longDescription", required = false)
  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public Program dateTimes(ProgramDateTimes dateTimes) {
    this.dateTimes = dateTimes;
    return this;
  }

  /**
   * Get dateTimes
   * @return dateTimes
  */
  @Valid 
  @Schema(name = "dateTimes", required = false)
  public ProgramDateTimes getDateTimes() {
    return dateTimes;
  }

  public void setDateTimes(ProgramDateTimes dateTimes) {
    this.dateTimes = dateTimes;
  }

  public Program adultWarning(Boolean adultWarning) {
    this.adultWarning = adultWarning;
    return this;
  }

  /**
   * Get adultWarning
   * @return adultWarning
  */
  
  @Schema(name = "adultWarning", required = false)
  public Boolean getAdultWarning() {
    return adultWarning;
  }

  public void setAdultWarning(Boolean adultWarning) {
    this.adultWarning = adultWarning;
  }

  public Program isLive(Boolean isLive) {
    this.isLive = isLive;
    return this;
  }

  /**
   * Get isLive
   * @return isLive
  */
  
  @Schema(name = "isLive", required = false)
  public Boolean getIsLive() {
    return isLive;
  }

  public void setIsLive(Boolean isLive) {
    this.isLive = isLive;
  }

  public Program primeur(Boolean primeur) {
    this.primeur = primeur;
    return this;
  }

  /**
   * Get primeur
   * @return primeur
  */
  
  @Schema(name = "primeur", required = false)
  public Boolean getPrimeur() {
    return primeur;
  }

  public void setPrimeur(Boolean primeur) {
    this.primeur = primeur;
  }

  public Program hasSubtitle(Boolean hasSubtitle) {
    this.hasSubtitle = hasSubtitle;
    return this;
  }

  /**
   * Get hasSubtitle
   * @return hasSubtitle
  */
  
  @Schema(name = "hasSubtitle", required = false)
  public Boolean getHasSubtitle() {
    return hasSubtitle;
  }

  public void setHasSubtitle(Boolean hasSubtitle) {
    this.hasSubtitle = hasSubtitle;
  }

  public Program replica(Boolean replica) {
    this.replica = replica;
    return this;
  }

  /**
   * Get replica
   * @return replica
  */
  
  @Schema(name = "replica", required = false)
  public Boolean getReplica() {
    return replica;
  }

  public void setReplica(Boolean replica) {
    this.replica = replica;
  }

  public Program geoBlocked(Boolean geoBlocked) {
    this.geoBlocked = geoBlocked;
    return this;
  }

  /**
   * Get geoBlocked
   * @return geoBlocked
  */
  
  @Schema(name = "geoBlocked", required = false)
  public Boolean getGeoBlocked() {
    return geoBlocked;
  }

  public void setGeoBlocked(Boolean geoBlocked) {
    this.geoBlocked = geoBlocked;
  }

  public Program longHtmlDesciption(String longHtmlDesciption) {
    this.longHtmlDesciption = longHtmlDesciption;
    return this;
  }

  /**
   * Get longHtmlDesciption
   * @return longHtmlDesciption
  */
  
  @Schema(name = "longHtmlDesciption", required = false)
  public String getLongHtmlDesciption() {
    return longHtmlDesciption;
  }

  public void setLongHtmlDesciption(String longHtmlDesciption) {
    this.longHtmlDesciption = longHtmlDesciption;
  }

  public Program productionYear(String productionYear) {
    this.productionYear = productionYear;
    return this;
  }

  /**
   * Get productionYear
   * @return productionYear
  */
  
  @Schema(name = "productionYear", required = false)
  public String getProductionYear() {
    return productionYear;
  }

  public void setProductionYear(String productionYear) {
    this.productionYear = productionYear;
  }

  public Program episodeNumber(String episodeNumber) {
    this.episodeNumber = episodeNumber;
    return this;
  }

  /**
   * Get episodeNumber
   * @return episodeNumber
  */
  
  @Schema(name = "episodeNumber", required = false)
  public String getEpisodeNumber() {
    return episodeNumber;
  }

  public void setEpisodeNumber(String episodeNumber) {
    this.episodeNumber = episodeNumber;
  }

  public Program people(List<ProgramPeopleInner> people) {
    this.people = people;
    return this;
  }

  public Program addPeopleItem(ProgramPeopleInner peopleItem) {
    if (this.people == null) {
      this.people = new ArrayList<>();
    }
    this.people.add(peopleItem);
    return this;
  }

  /**
   * Get people
   * @return people
  */
  @Valid 
  @Schema(name = "people", required = false)
  public List<ProgramPeopleInner> getPeople() {
    return people;
  }

  public void setPeople(List<ProgramPeopleInner> people) {
    this.people = people;
  }

  public Program image(ProgramImage image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  @Valid 
  @Schema(name = "image", required = false)
  public ProgramImage getImage() {
    return image;
  }

  public void setImage(ProgramImage image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Program program = (Program) o;
    return Objects.equals(this.title, program.title) &&
        Objects.equals(this.shortDescription, program.shortDescription) &&
        Objects.equals(this.longDescription, program.longDescription) &&
        Objects.equals(this.dateTimes, program.dateTimes) &&
        Objects.equals(this.adultWarning, program.adultWarning) &&
        Objects.equals(this.isLive, program.isLive) &&
        Objects.equals(this.primeur, program.primeur) &&
        Objects.equals(this.hasSubtitle, program.hasSubtitle) &&
        Objects.equals(this.replica, program.replica) &&
        Objects.equals(this.geoBlocked, program.geoBlocked) &&
        Objects.equals(this.longHtmlDesciption, program.longHtmlDesciption) &&
        Objects.equals(this.productionYear, program.productionYear) &&
        Objects.equals(this.episodeNumber, program.episodeNumber) &&
        Objects.equals(this.people, program.people) &&
        Objects.equals(this.image, program.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, shortDescription, longDescription, dateTimes, adultWarning, isLive, primeur, hasSubtitle, replica, geoBlocked, longHtmlDesciption, productionYear, episodeNumber, people, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Program {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
    sb.append("    longDescription: ").append(toIndentedString(longDescription)).append("\n");
    sb.append("    dateTimes: ").append(toIndentedString(dateTimes)).append("\n");
    sb.append("    adultWarning: ").append(toIndentedString(adultWarning)).append("\n");
    sb.append("    isLive: ").append(toIndentedString(isLive)).append("\n");
    sb.append("    primeur: ").append(toIndentedString(primeur)).append("\n");
    sb.append("    hasSubtitle: ").append(toIndentedString(hasSubtitle)).append("\n");
    sb.append("    replica: ").append(toIndentedString(replica)).append("\n");
    sb.append("    geoBlocked: ").append(toIndentedString(geoBlocked)).append("\n");
    sb.append("    longHtmlDesciption: ").append(toIndentedString(longHtmlDesciption)).append("\n");
    sb.append("    productionYear: ").append(toIndentedString(productionYear)).append("\n");
    sb.append("    episodeNumber: ").append(toIndentedString(episodeNumber)).append("\n");
    sb.append("    people: ").append(toIndentedString(people)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

