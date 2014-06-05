package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TaxonomyUrls.REST_TAXONOMY;

import uk.ac.ebi.ena.taxonomy.client.model.FetchByScientificNameRequest;
import uk.ac.ebi.ena.taxonomy.client.model.FetchByTaxIdRequest;
import uk.ac.ebi.ena.taxonomy.client.model.FetchRequest;
import uk.ac.ebi.ena.taxonomy.client.model.MatchNameRequest;
import uk.ac.ebi.ena.taxonomy.client.model.Taxon;
import uk.ac.ebi.ena.taxonomy.client.model.TaxonomyResponse;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TaxonomyClientImpl implements TaxonomyClient {

  private static final String SERVICE_UNAVAILABLE = "The service is currently unavailable, please try again later.";

  private final RestTemplate restTemplate;

  private final String webServiceUrl;

  public TaxonomyClientImpl(final String webServiceUrl) {
    restTemplate = new RestTemplate();
    this.webServiceUrl = webServiceUrl;
  }

  @Override
  public Taxon getTaxonById(final long taxId) {
    final FetchByTaxIdRequest fetchRequest = new FetchByTaxIdRequest.Builder().tax_id(taxId).build();
    return fetchTaxon(fetchRequest);
  }

  @Override
  public Taxon getTaxonByScientificName(final String scientificName) {
    final FetchByScientificNameRequest fetchRequest = new FetchByScientificNameRequest.Builder().scientific_name(scientificName).build();
    return fetchTaxon(fetchRequest);
  }

  @Override
  public boolean isScientificNameValid(final String scientificName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Taxon> suggestTaxa(final String partialName) {
    final MatchNameRequest matchNameRequest = new MatchNameRequest.Builder().name(partialName).build();
    return suggestTaxa(matchNameRequest);
  }

  @Override
  public List<Taxon> suggestTaxa(final String partialName, final boolean metagenome) throws TaxonomyException {
    final MatchNameRequest matchNameRequest = new MatchNameRequest.Builder().name(partialName).metagenome(metagenome).build();
    return suggestTaxa(matchNameRequest);
  }

  @Override
  public List<Taxon> suggestTaxa(final String partialName, final boolean metagenome, final int limit) throws TaxonomyException {
    final MatchNameRequest matchNameRequest = new MatchNameRequest.Builder().name(partialName).metagenome(metagenome).limit(limit).build();
    return suggestTaxa(matchNameRequest);
  }

  @Override
  public List<Taxon> suggestTaxa(final String partialName, final int limit) throws TaxonomyException {
    final MatchNameRequest matchNameRequest = new MatchNameRequest.Builder().name(partialName).limit(limit).build();
    return suggestTaxa(matchNameRequest);
  }

  private Taxon fetchTaxon(final FetchRequest fetchRequest) {
    try {
      final String requestUrl = webServiceUrl + REST_TAXONOMY;
      final TaxonomyResponse taxonomyResponse =
          restTemplate.postForObject(requestUrl, fetchRequest, TaxonomyResponse.class);
      return taxonomyResponse.getTaxa().get(0);
    } catch (final ResourceAccessException e) {
      throw new TaxonomyException(SERVICE_UNAVAILABLE);
    } catch (final Exception e) {
      throw new TaxonomyException(e.getMessage());
    }
  }

  private List<Taxon> suggestTaxa(final MatchNameRequest matchNameRequest) {
    try {
      final String requestUrl = webServiceUrl + REST_TAXONOMY;
      final TaxonomyResponse taxonomyResponse =
          restTemplate.postForObject(requestUrl, matchNameRequest, TaxonomyResponse.class);
      return taxonomyResponse.getTaxa();
    } catch (final ResourceAccessException e) {
      throw new TaxonomyException(SERVICE_UNAVAILABLE);
    } catch (final Exception e) {
      throw new TaxonomyException(e.getMessage());
    }
  }
}
