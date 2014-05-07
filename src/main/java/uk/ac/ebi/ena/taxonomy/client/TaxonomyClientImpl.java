package uk.ac.ebi.ena.taxonomy.client;

import static uk.ac.ebi.ena.taxonomy.client.TaxonomyUrls.REST_TAXONOMY;

import uk.ac.ebi.ena.taxonomy.client.model.Taxon;
import uk.ac.ebi.ena.taxonomy.client.model.TaxonomyRequest;
import uk.ac.ebi.ena.taxonomy.client.model.TaxonomyRequest.Task;
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
  public List<Taxon> suggestTaxa(String partialName) {
   try
      {
        final String requestUrl = webServiceUrl  + REST_TAXONOMY;
        final TaxonomyRequest taxonomyRequest = new TaxonomyRequest();
        taxonomyRequest.setTask(Task.match_name);
        taxonomyRequest.setName(partialName);
        final TaxonomyResponse taxonomyResponse = restTemplate.postForObject(requestUrl, taxonomyRequest, TaxonomyResponse.class);
        return taxonomyResponse.getTaxa();
      } catch (final ResourceAccessException e) {
        throw new TaxonomyException(SERVICE_UNAVAILABLE);
      }
  }
}
