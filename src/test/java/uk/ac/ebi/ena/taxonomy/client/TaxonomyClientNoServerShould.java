package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;
import org.junit.Test;

public class TaxonomyClientNoServerShould {

  private static final String MISSING_SERVER_URL = "http://localhost/nothing";

  private TaxonomyClient taxonomyClient;

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientImpl(MISSING_SERVER_URL);
  }

  @Test (expected = TaxonomyException.class)
  public void test_suggestTaxa_with_invalid_session_id() throws TaxonomyException {
    taxonomyClient.suggestTaxa("partialName");
  }

  @Test (expected = TaxonomyException.class)
  public void test_getTaxonById_with_invalid_session_id() throws TaxonomyException {
    taxonomyClient.getTaxonById(1234);
  }
  
}
