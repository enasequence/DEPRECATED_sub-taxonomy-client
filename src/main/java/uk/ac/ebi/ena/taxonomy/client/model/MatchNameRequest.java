package uk.ac.ebi.ena.taxonomy.client.model;

public class MatchNameRequest {

  public static class Builder {
    private String name;
    private int limit = 20;
    private boolean metagenome;

    public MatchNameRequest build() {
      return new MatchNameRequest(this);
    }

    public Builder limit(final int limit) {
      this.limit = limit;
      return this;
    }

    public Builder metagenome(final boolean metagenome) {
      this.metagenome = metagenome;
      return this;
    }

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

  }

  private final Task task = Task.match_name;
  private String name;
  private int limit;
  private boolean metagenome;

  private MatchNameRequest(final Builder builder) {
    name = builder.name;
    limit = builder.limit;
    metagenome = builder.metagenome;
  }

  public int getLimit() {
    return limit;
  }

  public String getName() {
    return name;
  }

  public Task getTask() {
    return task;
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

}
