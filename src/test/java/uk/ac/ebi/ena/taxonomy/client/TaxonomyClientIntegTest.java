package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.ena.taxonomy.client.model.LegacyTaxon;
import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

import static org.junit.Assert.*;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.*;

public abstract class TaxonomyClientIntegTest {

  private static final int INVALID_TAX_ID = 9607;
  private static final int LIMIT = 10;
  protected TaxonomyClient taxonomyClient;

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
    final List<Taxon> taxa = taxonomyClient.getTaxonByScientificName(TAXONOMY_SCIENTIFIC_NAME_QUERY);
    assertNotNull(taxa.get(0));
    assertTrue(TAXONOMY_ID_QUERY==taxa.get(0).getTaxId());
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
