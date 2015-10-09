package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;

import static uk.ac.ebi.ena.taxonomy.client.TestConstants.SERVICE_URL;

public class TaxonomyClientCurrentIntegTest extends TaxonomyClientIntegTest {

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientCurrentImpl(SERVICE_URL);
  }

}
