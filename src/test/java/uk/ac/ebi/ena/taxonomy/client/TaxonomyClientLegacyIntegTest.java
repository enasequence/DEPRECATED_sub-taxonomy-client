package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.ena.taxonomy.client.model.LegacyTaxon;
import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

import static org.junit.Assert.*;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.*;

public class TaxonomyClientLegacyIntegTest extends TaxonomyClientIntegTest {

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientLegacyImpl(LEGACY_SERVICE_URL);
  }

}
