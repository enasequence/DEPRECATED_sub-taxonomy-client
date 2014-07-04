package uk.ac.ebi.ena.taxonomy.client.model;

public class ValidationResponse {

  private boolean valid;
  private String scientific_name;

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
    final ValidationResponse other = (ValidationResponse) obj;
    if (scientific_name == null) {
      if (other.scientific_name != null) {
        return false;
      }
    } else if (!scientific_name.equals(other.scientific_name)) {
      return false;
    }
    if (valid != other.valid) {
      return false;
    }
    return true;
  }

  public String getScientific_name() {
    return scientific_name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((scientific_name == null) ? 0 : scientific_name.hashCode());
    result = (prime * result) + (valid ? 1231 : 1237);
    return result;
  }

  public boolean getValid() {
    return valid;
  }

  public void setScientific_name(final String scientific_name) {
    this.scientific_name = scientific_name;
  }

  public void setValid(final boolean valid) {
    this.valid = valid;
  }

  @Override
  public String toString() {
    return "ValidationResponse [valid=" + valid + ", scientific_name=" + scientific_name + "]";
  }

}
