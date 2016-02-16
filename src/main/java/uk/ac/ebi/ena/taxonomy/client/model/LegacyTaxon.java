package uk.ac.ebi.ena.taxonomy.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegacyTaxon implements Taxon {

  @JsonProperty("tax_id")
  private Long taxId = 0L;
  private String name;
  @JsonProperty("scientific_name")
  private String scientificName;
  @JsonProperty("common_name")
  private String commonName;
  private String rank;

  public void setSubmittable(boolean submittable) {
    this.submittable = submittable;
  }

  private boolean submittable = false;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LegacyTaxon that = (LegacyTaxon) o;

    if (submittable != that.submittable) {
      return false;
    }
    if (taxId != null ? !taxId.equals(that.taxId) : that.taxId != null) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (scientificName != null ? !scientificName.equals(that.scientificName) : that.scientificName != null) {
      return false;
    }
    if (commonName != null ? !commonName.equals(that.commonName) : that.commonName != null) {
      return false;
    }
    if (rank != null ? !rank.equals(that.rank) : that.rank != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = taxId != null ? taxId.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (scientificName != null ? scientificName.hashCode() : 0);
    result = 31 * result + (commonName != null ? commonName.hashCode() : 0);
    result = 31 * result + (rank != null ? rank.hashCode() : 0);
    result = 31 * result + (submittable ? 1 : 0);
    return result;
  }

  public String getCommonName() {
    return commonName;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean isSubmittable() {
    return submittable;
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
    return "LegacyTaxon{" +
            "taxId=" + taxId +
            ", name='" + name + '\'' +
            ", scientificName='" + scientificName + '\'' +
            ", commonName='" + commonName + '\'' +
            ", rank='" + rank + '\'' +
            ", submittable=" + submittable +
            '}';
  }
}
