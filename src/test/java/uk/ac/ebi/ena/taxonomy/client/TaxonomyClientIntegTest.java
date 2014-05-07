package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TestConstants.SERVICE_URL;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.TAXONOMY_QUERY;

import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TaxonomyClientIntegTest {

  private TaxonomyClient taxonomyClient;

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientImpl(SERVICE_URL);
  }

  @Test
  public void test_suggest_taxa()  throws Exception {
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_QUERY);
    assertFalse(taxonSuggestionList.isEmpty());
    for (final Taxon taxon : taxonSuggestionList)
    {
      assertTrue(taxon.getName().startsWith(TAXONOMY_QUERY));
    }
  }
}
