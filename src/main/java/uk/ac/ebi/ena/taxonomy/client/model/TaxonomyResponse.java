package uk.ac.ebi.ena.taxonomy.client.model;

import java.util.List;

public class TaxonomyResponse {

  private int limit;
  private boolean metagenome;
  private String name;
  private List<Taxon> taxa;
  
  public int getLimit() {
    return limit;
  }
  public String getName() {
    return name;
  }
  public List<Taxon> getTaxa() {
    return taxa;
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
  public void setTaxa(final List<Taxon> taxa) {
    this.taxa = taxa;
  }
}
