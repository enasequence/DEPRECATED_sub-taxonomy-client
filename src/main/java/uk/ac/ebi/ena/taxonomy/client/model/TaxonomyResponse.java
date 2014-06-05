package uk.ac.ebi.ena.taxonomy.client.model;

import java.util.List;

public class TaxonomyResponse {

  private int limit;
  private boolean metagenome;
  private String tax_id;
  private String scientific_name;
  private String name;
  private List<Taxon> taxa;

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
    final TaxonomyResponse other = (TaxonomyResponse) obj;
    if (limit != other.limit) {
      return false;
    }
    if (metagenome != other.metagenome) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (tax_id == null) {
      if (other.tax_id != null) {
        return false;
      }
    } else if (!tax_id.equals(other.tax_id)) {
      return false;
    }
    if (taxa == null) {
      if (other.taxa != null) {
        return false;
      }
    } else if (!taxa.equals(other.taxa)) {
      return false;
    }
    return true;
  }
  public int getLimit() {
    return limit;
  }

  public String getName() {
    return name;
  }

  public String getScientific_name() {
    return scientific_name;
  }

  public String getTax_id() {
    return tax_id;
  }

  public List<Taxon> getTaxa() {
    return taxa;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + limit;
    result = (prime * result) + (metagenome ? 1231 : 1237);
    result = (prime * result) + ((name == null) ? 0 : name.hashCode());
    result = (prime * result) + ((tax_id == null) ? 0 : tax_id.hashCode());
    result = (prime * result) + ((taxa == null) ? 0 : taxa.hashCode());
    return result;
  }

  public boolean isMetagenome() {
    return metagenome;
  }

  public void setLimit(final int limit) {
    this.limit = limit;
  }

  public void setMetagenome(final boolean metagenome) {
    this.metagenome = metagenome;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setScientific_name(final String scientific_name) {
    this.scientific_name = scientific_name;
  }

  public void setTax_id(final String tax_id) {
    this.tax_id = tax_id;
  }

  public void setTaxa(final List<Taxon> taxa) {
    this.taxa = taxa;
  }

  @Override
  public String toString() {
    return "TaxonomyResponse [limit=" + limit + ", metagenome=" + metagenome + ", tax_id=" + tax_id + ", name=" + name
        + ", taxa=" + taxa + "]";
  }


}
