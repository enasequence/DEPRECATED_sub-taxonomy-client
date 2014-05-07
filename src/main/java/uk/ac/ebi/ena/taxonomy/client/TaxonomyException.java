package uk.ac.ebi.ena.taxonomy.client;

public class TaxonomyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TaxonomyException() {
    super();
  }

  public TaxonomyException(final String message) {
    super(message);
  }


  public TaxonomyException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TaxonomyException(final Throwable cause) {
    super(cause);
  }

}