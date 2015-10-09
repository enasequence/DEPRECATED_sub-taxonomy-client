package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;
import org.junit.Test;

public class TaxonomyClientLegacyNoServerShould extends TaxonomyClientNoServerShould {

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientLegacyImpl(MISSING_SERVER_URL);
  }
  
}
