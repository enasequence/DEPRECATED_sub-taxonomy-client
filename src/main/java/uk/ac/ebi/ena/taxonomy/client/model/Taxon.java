package uk.ac.ebi.ena.taxonomy.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Taxon {

  @JsonProperty("tax_id")
  private Long taxId;
  private String name;
  @JsonProperty("scientific_name")
  private String scientificName;
  @JsonProperty("common_name")
  private String commonName;
  private String rank;

  public String getCommonName() {
    return commonName;
  }
  
  public String getName() {
    return name;
  }
  
  public String getRank() {
    return rank;
  }
  
  public String getScientificName() {
    return scientificName;
  }
  
  public Long getTaxId() {
    return taxId;
  }
  
  public void setCommonName(final String commonName) {
    this.commonName = commonName;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  public void setRank(final String rank) {
    this.rank = rank;
  }
  
  public void setScientificName(final String scientificName) {
    this.scientificName = scientificName;
  }
  
  public void setTaxId(final Long taxId) {
    this.taxId = taxId;
  }
  
  @Override
  public String toString() {
    return "Taxon [taxId=" + taxId + ", name=" + name + ", scientificName=" + scientificName + ", commonName="
        + commonName + ", rank=" + rank + "]";
  }
}
