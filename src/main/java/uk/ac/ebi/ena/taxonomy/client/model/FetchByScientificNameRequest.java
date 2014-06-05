package uk.ac.ebi.ena.taxonomy.client.model;

public class FetchByScientificNameRequest implements FetchRequest {

  public static class Builder {
    private String scientific_name;
    private boolean metagenome;
    private String code;

    public FetchByScientificNameRequest build() {
      return new FetchByScientificNameRequest(this);
    }

    public Builder code(final String code) {
      this.code = code;
      return this;
    }

    public Builder metagenome(final boolean metagenome) {
      this.metagenome = metagenome;
      return this;
    }
    public Builder scientific_name(final String scientific_name) {
      this.scientific_name = scientific_name;
      return this;
    }

  }

  private final Task task = Task.fetch;
  private String scientific_name;
  private final boolean metagenome;
  private String code;

  private FetchByScientificNameRequest(final Builder builder) {
    scientific_name = builder.scientific_name;
    metagenome = builder.metagenome;
    code = builder.code;
  }

  public String getCode() {
    return code;
  }

  public String getScientific_name() {
    return scientific_name;
  }

  public Task getTask() {
    return task;
  }

  public boolean isMetagenome() {
    return metagenome;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public void setScientific_name(final String scientific_name) {
    this.scientific_name = scientific_name;
  }


}
