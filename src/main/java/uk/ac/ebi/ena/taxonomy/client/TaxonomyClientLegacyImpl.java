package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TaxonomyUrls.REST_TAXONOMY;

import uk.ac.ebi.ena.taxonomy.client.model.*;
import uk.ac.ebi.ena.taxonomy.client.model.LegacyTaxon;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxonomyClientLegacyImpl implements TaxonomyClient {

  private static final String SERVICE_UNAVAILABLE = "The service is currently unavailable, please try again later.";

  private final RestTemplate restTemplate;

  private final String webServiceUrl;

  public TaxonomyClientLegacyImpl(final String webServiceUrl) {
    restTemplate = new RestTemplate();
    this.webServiceUrl = webServiceUrl;
  }

  @Override
  public Taxon getTaxonById(final long taxId) {
    final FetchByTaxIdRequest fetchRequest = new FetchByTaxIdRequest.Builder().tax_id(taxId).build();
    return fetchTaxon(fetchRequest);
  }

  @Override
  public List<Taxon> getTaxonByScientificName(final String scientificName) {
    final FetchByScientificNameRequest fetchRequest = new FetchByScientificNameRequest.Builder().scientific_name(scientificName).build();
    return Arrays.asList(fetchTaxon(fetchRequest));
  }

  @Override
  public boolean isScientificNameValid(final String scientificName) {
    final ValidateRequest validateRequest = new ValidateRequest.Builder().scientific_name(scientificName).build();
    return validate(validateRequest);
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
  
  private boolean validate(final ValidateRequest validateRequest) {
    try {
      final String requestUrl = webServiceUrl + REST_TAXONOMY;
      final ValidationResponse validationResponse =
          restTemplate.postForObject(requestUrl, validateRequest, ValidationResponse.class);
      return validationResponse.getValid();
    } catch (final ResourceAccessException e) {
      throw new TaxonomyException(SERVICE_UNAVAILABLE);
    } catch (final Exception e) {
      throw new TaxonomyException(e.getMessage());
    }
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
      return new LegacyTaxon();
    }
  }

  private List<Taxon> suggestTaxa(final MatchNameRequest matchNameRequest) {
    try {
      final String requestUrl = webServiceUrl + REST_TAXONOMY;
      final TaxonomyResponse taxonomyResponse =
          restTemplate.postForObject(requestUrl, matchNameRequest, TaxonomyResponse.class);
      return new ArrayList<Taxon>(taxonomyResponse.getTaxa());
    } catch (final ResourceAccessException e) {
      throw new TaxonomyException(SERVICE_UNAVAILABLE);
    } catch (final Exception e) {
      throw new TaxonomyException(e.getMessage());
    }
  }

  @Override
  public boolean isTaxIdValid(long taxId) throws TaxonomyException {
    Taxon taxon = getTaxonById(taxId);
    return taxon.getTaxId() > 0;
  }
}
