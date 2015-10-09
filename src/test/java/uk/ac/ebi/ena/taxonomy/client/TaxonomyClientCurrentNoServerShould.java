package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;

public class TaxonomyClientCurrentNoServerShould extends TaxonomyClientNoServerShould {

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientCurrentImpl(MISSING_SERVER_URL);
  }
  
}
