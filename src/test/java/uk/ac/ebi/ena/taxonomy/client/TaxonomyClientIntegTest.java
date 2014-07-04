package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TestConstants.SERVICE_URL;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.TAXONOMY_ID_QUERY;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.*;

import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TaxonomyClientIntegTest {

  private static final int INVALID_TAX_ID = 9604;
  private static final int LIMIT = 10;
  private TaxonomyClient taxonomyClient;

  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientImpl(SERVICE_URL);
  }

  @Test
  public void test_suggest_taxa()  throws Exception {
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_NAME_QUERY);
    assertFalse(taxonSuggestionList.isEmpty());
    for (final Taxon taxon : taxonSuggestionList)
    {
      assertTrue(taxon.getName().startsWith(TAXONOMY_NAME_QUERY));
    }
  }
  
  @Test
  public void test_suggest_taxa_with_limit()  throws Exception {
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_NAME_QUERY, LIMIT);
    assertFalse(taxonSuggestionList.isEmpty());
    assertEquals(LIMIT,taxonSuggestionList.size());
    for (final Taxon taxon : taxonSuggestionList)
    {
      assertTrue(taxon.getName().startsWith(TAXONOMY_NAME_QUERY));
    }
  }
  
  @Test
  public void test_suggest_taxa_with_meta_genome()  throws Exception {
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_NAME_QUERY, false);
    assertFalse(taxonSuggestionList.isEmpty());
    for (final Taxon taxon : taxonSuggestionList)
    {
      assertTrue(taxon.getName().startsWith(TAXONOMY_NAME_QUERY));
    }
  }
  
  @Test
  public void test_suggest_taxa_with_meta_genome_and_limit()  throws Exception {
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_NAME_QUERY, false, 10);
    assertFalse(taxonSuggestionList.isEmpty());
    assertEquals(LIMIT,taxonSuggestionList.size());
    for (final Taxon taxon : taxonSuggestionList)
    {
      assertTrue(taxon.getName().startsWith(TAXONOMY_NAME_QUERY));
    }
  }
  
  @Test
  public void test_get_taxon_by_id()  throws Exception {
    final Taxon taxon = taxonomyClient.getTaxonById(TAXONOMY_ID_QUERY);
    assertNotNull(taxon);
    assertTrue(TAXONOMY_ID_QUERY==taxon.getTaxId());
  }
  
  @Test
  public void test_invalid_get_taxon_by_id()  throws Exception {
    final Taxon taxon = taxonomyClient.getTaxonById(INVALID_TAX_ID);
    assertNotNull(taxon);
    assertTrue(0L==taxon.getTaxId());
  }
  
  @Test
  public void test_get_taxon_by_scientificName()  throws Exception {
    final Taxon taxon = taxonomyClient.getTaxonByScientificName(TAXONOMY_SCIENTIFIC_NAME_QUERY);
    assertNotNull(taxon);
    assertTrue(TAXONOMY_ID_QUERY==taxon.getTaxId());
  }
  
  @Test
  public void test_isScientificNameValid()  throws Exception {
    final boolean valid = taxonomyClient.isScientificNameValid(TAXONOMY_SCIENTIFIC_NAME_QUERY);
    assertTrue(valid);
  }
  
  @Test
  public void test_invalid_isScientificNameValid()  throws Exception {
    final boolean valid = taxonomyClient.isScientificNameValid("fish");
    assertFalse(valid);
  }
  
  @Test
  public void test_isTaxIdValid() throws Exception {
    final boolean valid = taxonomyClient.isTaxIdValid(TAXONOMY_ID_QUERY);
    assertTrue(valid);
  }

  @Test
  public void test_invalid_isTaxIdValid() throws Exception {
    final boolean valid = taxonomyClient.isTaxIdValid(INVALID_TAX_ID);
    assertFalse(valid);
  }
}
