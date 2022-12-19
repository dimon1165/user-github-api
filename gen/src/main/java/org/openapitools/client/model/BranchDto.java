/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * BranchDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-18T20:07:04.552370+02:00[Europe/Kiev]")
public class BranchDto {
  public static final String SERIALIZED_NAME_BRANCH_NAME = "branchName";
  @SerializedName(SERIALIZED_NAME_BRANCH_NAME)
  private String branchName;

  public static final String SERIALIZED_NAME_LAST_COMMIT_SHA = "lastCommitSha";
  @SerializedName(SERIALIZED_NAME_LAST_COMMIT_SHA)
  private String lastCommitSha;


  public BranchDto branchName(String branchName) {
    
    this.branchName = branchName;
    return this;
  }

   /**
   * Get branchName
   * @return branchName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBranchName() {
    return branchName;
  }


  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }


  public BranchDto lastCommitSha(String lastCommitSha) {
    
    this.lastCommitSha = lastCommitSha;
    return this;
  }

   /**
   * Get lastCommitSha
   * @return lastCommitSha
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLastCommitSha() {
    return lastCommitSha;
  }


  public void setLastCommitSha(String lastCommitSha) {
    this.lastCommitSha = lastCommitSha;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BranchDto branchDto = (BranchDto) o;
    return Objects.equals(this.branchName, branchDto.branchName) &&
        Objects.equals(this.lastCommitSha, branchDto.lastCommitSha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branchName, lastCommitSha);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BranchDto {\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    lastCommitSha: ").append(toIndentedString(lastCommitSha)).append("\n");
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

