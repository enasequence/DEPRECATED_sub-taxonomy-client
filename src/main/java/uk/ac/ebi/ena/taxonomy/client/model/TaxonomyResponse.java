package uk.ac.ebi.ena.taxonomy.client.model;

import java.util.List;

public class TaxonomyResponse {

  private int limit;
  private boolean metagenome;
  private String tax_id;
  private String scientific_name;
  private String name;
  private List<Taxon> taxa;

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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TaxonomyResponse that = (TaxonomyResponse) o;

    if (limit != that.limit) return false;
    if (metagenome != that.metagenome) return false;
    if (tax_id != null ? !tax_id.equals(that.tax_id) : that.tax_id != null) return false;
    if (scientific_name != null ? !scientific_name.equals(that.scientific_name) : that.scientific_name != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (taxa != null ? !taxa.equals(that.taxa) : that.taxa != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = limit;
    result = 31 * result + (metagenome ? 1 : 0);
    result = 31 * result + (tax_id != null ? tax_id.hashCode() : 0);
    result = 31 * result + (scientific_name != null ? scientific_name.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (taxa != null ? taxa.hashCode() : 0);
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
    return "TaxonomyResponse{" +
            "limit=" + limit +
            ", metagenome=" + metagenome +
            ", tax_id='" + tax_id + '\'' +
            ", scientific_name='" + scientific_name + '\'' +
            ", name='" + name + '\'' +
            ", taxa=" + taxa +
            '}';
  }


}
