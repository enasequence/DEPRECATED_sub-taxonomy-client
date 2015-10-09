package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TaxonomyUrls.REST_TAXONOMY;
import static uk.ac.ebi.ena.taxonomy.client.TestConstants.*;

import uk.ac.ebi.ena.taxonomy.client.model.LegacyTaxon;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

public class TaxonomyClientShould {

  @ClassRule
  public static final WireMockClassRule WIRE_MOCK_RULE = new WireMockClassRule(MOCK_SERVER_PORT);

  private TaxonomyClient taxonomyClient;

  @Test
  public void return_200_and_taxon_suggestions_for_suggestTaxa() {
    final String url = REST_TAXONOMY;
    stubFor(post(urlEqualTo(url))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBody("{\"limit\":20,\"metagenome\":false,\"name\":\"Mus\",\"taxa\":[{\"rank\":\"species\",\"name\":\"Mus abbotti\",\"common_name\":\"Abbott's mouse\",\"tax_id\":10108,\"scientific_name\":\"Mus abbotti\"},{\"rank\":\"subspecies\",\"name\":\"Mus bactrianus\",\"common_name\":\"southwestern Asian house mouse\",\"tax_id\":35531,\"scientific_name\":\"Mus musculus bactrianus\"},{\"rank\":\"species\",\"name\":\"Mus baoulei\",\"common_name\":\"Baoule's mouse\",\"tax_id\":544437,\"scientific_name\":\"Mus baoulei\"},{\"rank\":\"species\",\"name\":\"Mus bella\",\"common_name\":\"Temminck's mouse\",\"tax_id\":60742,\"scientific_name\":\"Mus musculoides\"},{\"rank\":\"species\",\"name\":\"Mus bellus enclavae\",\"common_name\":\"Temminck's mouse\",\"tax_id\":60742,\"scientific_name\":\"Mus musculoides\"},{\"rank\":\"species\",\"name\":\"Mus booduga\",\"common_name\":\"little Indian field mouse\",\"tax_id\":27681,\"scientific_name\":\"Mus booduga\"},{\"rank\":\"species\",\"name\":\"Mus bufo\",\"common_name\":\"toad mouse\",\"tax_id\":397332,\"scientific_name\":\"Mus bufo\"},{\"rank\":\"species\",\"name\":\"Mus callewaerti\",\"common_name\":\"Callewaert's mouse\",\"tax_id\":1380868,\"scientific_name\":\"Mus callewaerti\"},{\"rank\":\"species\",\"name\":\"Mus caroli\",\"common_name\":\"Ryukyu mouse\",\"tax_id\":10089,\"scientific_name\":\"Mus caroli\"},{\"rank\":\"species\",\"name\":\"Mus caroli cytomegalovirus 1\",\"common_name\":null,\"tax_id\":431628,\"scientific_name\":\"Mus caroli cytomegalovirus 1\"},{\"rank\":\"species\",\"name\":\"Mus caroli rhadinovirus 1\",\"common_name\":null,\"tax_id\":398034,\"scientific_name\":\"Mus caroli rhadinovirus 1\"},{\"rank\":\"subspecies\",\"name\":\"Mus castaneus\",\"common_name\":\"southeastern Asian house mouse\",\"tax_id\":10091,\"scientific_name\":\"Mus musculus castaneus\"},{\"rank\":\"species\",\"name\":\"Mus cervicolor\",\"common_name\":\"fawn-colored mouse\",\"tax_id\":10097,\"scientific_name\":\"Mus cervicolor\"},{\"rank\":\"subspecies\",\"name\":\"Mus cervicolor cervicolor\",\"common_name\":null,\"tax_id\":135827,\"scientific_name\":\"Mus cervicolor cervicolor\"},{\"rank\":\"species\",\"name\":\"Mus cervicolor cytomegalovirus 1\",\"common_name\":null,\"tax_id\":456489,\"scientific_name\":\"Mus cervicolor cytomegalovirus 1\"},{\"rank\":\"subspecies\",\"name\":\"Mus cervicolor popaeus\",\"common_name\":null,\"tax_id\":135828,\"scientific_name\":\"Mus cervicolor popaeus\"},{\"rank\":\"no rank\",\"name\":\"Mus cervicolor popaeus endogenous virus\",\"common_name\":null,\"tax_id\":153473,\"scientific_name\":\"Mus cervicolor popaeus endogenous virus\"},{\"rank\":\"species\",\"name\":\"Mus cervicolor rhadinovirus 1\",\"common_name\":null,\"tax_id\":456502,\"scientific_name\":\"Mus cervicolor rhadinovirus 1\"},{\"rank\":\"species\",\"name\":\"Mus cf. musculoides\",\"common_name\":null,\"tax_id\":397333,\"scientific_name\":\"Mus cf. musculoides\"},{\"rank\":\"species\",\"name\":\"Mus cf. saxicola\",\"common_name\":null,\"tax_id\":273920,\"scientific_name\":\"Mus cf. saxicola\"}]}")));
    final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(TAXONOMY_NAME_QUERY);
    assertFalse(taxonSuggestionList.isEmpty());
    for (final Taxon taxon : taxonSuggestionList) {
      assertTrue(taxon.getName().startsWith(TAXONOMY_NAME_QUERY));
      System.out.println(taxon.toString());
    }
    verify(postRequestedFor(urlEqualTo(url)));
  }

  @Test
  public void return_200_and_taxon_suggestions_for_getTaxonById() {
    final String url = REST_TAXONOMY;
    stubFor(post(urlEqualTo(url))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBody("{\"metagenome\":false,\"tax_id\":9606,\"taxa\":[{\"rank\":\"species\",\"name\":null,\"common_name\":\"human\",\"tax_id\":9606,\"scientific_name\":\"Homo sapiens\"}]}")));
    final Taxon taxon = taxonomyClient.getTaxonById(TAXONOMY_ID_QUERY);
    assertNotNull(taxon);
    assertTrue(TAXONOMY_ID_QUERY==taxon.getTaxId());
    System.out.println(taxon.toString());
    verify(postRequestedFor(urlEqualTo(url)));
  }

  @Test
  public void return_200_and_taxon_suggestions_for_getTaxonByScientificName() {
    final String url = REST_TAXONOMY;
    stubFor(post(urlEqualTo(url))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json;charset=UTF-8")
            .withBody("{\"taxa\":[{\"rank\":\"species\",\"name\":null,\"common_name\":\"human\",\"tax_id\":9606,\"scientific_name\":\"Homo sapiens\"}],\"scientific_name\":\"Homo sapiens\"}")));
    final List<Taxon> taxa = taxonomyClient.getTaxonByScientificName(TAXONOMY_SCIENTIFIC_NAME_QUERY);
    assertNotNull(taxa.get(0));
    assertEquals(TAXONOMY_SCIENTIFIC_NAME_QUERY,taxa.get(0).getScientificName());
    System.out.println(taxa.get(0).toString());
    verify(postRequestedFor(urlEqualTo(url)));
  }

   
  @Before
  public void setup() {
    taxonomyClient = new TaxonomyClientLegacyImpl("http://localhost:" + MOCK_SERVER_PORT);
  }

}
