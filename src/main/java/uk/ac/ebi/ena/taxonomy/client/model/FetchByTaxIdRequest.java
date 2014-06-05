package uk.ac.ebi.ena.taxonomy.client.model;

public class FetchByTaxIdRequest implements FetchRequest {

  public static class Builder {
    private long tax_id;
    private boolean metagenome;
    private String code;

    public FetchByTaxIdRequest build() {
      return new FetchByTaxIdRequest(this);
    }

    public Builder code(final String code) {
      this.code = code;
      return this;
    }

    public Builder metagenome(final boolean metagenome) {
      this.metagenome = metagenome;
      return this;
    }

    public Builder tax_id(final long tax_id) {
      this.tax_id = tax_id;
      return this;
    }

  }

  private final Task task = Task.fetch;
  private long tax_id;
  private final boolean metagenome;
  private String code;

  private FetchByTaxIdRequest(final Builder builder) {
    tax_id = builder.tax_id;
    metagenome = builder.metagenome;
    code = builder.code;
  }

  public String getCode() {
    return code;
  }

  public Task getTask() {
    return task;
  }

  public long getTax_id() {
    return tax_id;
  }

  public boolean isMetagenome() {
    return metagenome;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public void setTax_id(final long tax_id) {
    this.tax_id = tax_id;
  }

}
