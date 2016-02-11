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

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LegacyTaxon other = (LegacyTaxon) obj;
    if (commonName == null) {
      if (other.commonName != null) {
        return false;
      }
    } else if (!commonName.equals(other.commonName)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (rank == null) {
      if (other.rank != null) {
        return false;
      }
    } else if (!rank.equals(other.rank)) {
      return false;
    }
    if (scientificName == null) {
      if (other.scientificName != null) {
        return false;
      }
    } else if (!scientificName.equals(other.scientificName)) {
      return false;
    }
    if (taxId == null) {
      if (other.taxId != null) {
        return false;
      }
    } else if (!taxId.equals(other.taxId)) {
      return false;
    }
    return true;
  }

  public String getCommonName() {
    return commonName;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean isSubmittable() {
    return true;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((commonName == null) ? 0 : commonName.hashCode());
    result = (prime * result) + ((name == null) ? 0 : name.hashCode());
    result = (prime * result) + ((rank == null) ? 0 : rank.hashCode());
    result = (prime * result) + ((scientificName == null) ? 0 : scientificName.hashCode());
    result = (prime * result) + ((taxId == null) ? 0 : taxId.hashCode());
    return result;
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
    return "LegacyTaxon [taxId=" + taxId + ", name=" + name + ", scientificName=" + scientificName + ", commonName="
        + commonName + ", rank=" + rank + "]";
  }
}
