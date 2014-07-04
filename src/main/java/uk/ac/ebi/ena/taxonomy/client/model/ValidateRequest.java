package uk.ac.ebi.ena.taxonomy.client.model;

public class ValidateRequest implements FetchRequest {

  public static class Builder {
    private String scientific_name;

    public ValidateRequest build() {
      return new ValidateRequest(this);
    }

    public Builder scientific_name(final String scientific_name) {
      this.scientific_name = scientific_name;
      return this;
    }
  }

  private final Task task = Task.validate;
  private final String scientific_name;

  private ValidateRequest(final Builder builder) {
    scientific_name = builder.scientific_name;
  }

  public String getScientific_name() {
    return scientific_name;
  }

  public Task getTask() {
    return task;
  }

}
