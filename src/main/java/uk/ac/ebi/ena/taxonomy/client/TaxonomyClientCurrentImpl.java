package uk.ac.ebi.ena.taxonomy.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.ena.taxonomy.client.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxonomyClientCurrentImpl implements TaxonomyClient {

    private static final String SERVICE_UNAVAILABLE = "The service is currently unavailable, please try again later.";
    private static final String SERVICE_ERROR = "The service returned an error";
    private static final int DEFAULT_LIMIT = 10;

    private final RestTemplate restTemplate;

    private final String webServiceUrl;

    public TaxonomyClientCurrentImpl(final String webServiceUrl) {
        restTemplate = new RestTemplate();
        this.webServiceUrl = webServiceUrl;
    }

    @Override
    public List<Taxon> suggestTaxa(String partialName) throws TaxonomyException {
        return suggestTaxa(partialName, false, DEFAULT_LIMIT);
    }

    @Override
    public List<Taxon> suggestTaxa(String partialName, boolean metagenome) throws TaxonomyException {
        return suggestTaxa(partialName, metagenome, DEFAULT_LIMIT);
    }

    @Override
    public List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit) throws TaxonomyException {
        try {
            String requestUrl = webServiceUrl + "/v1/taxon/suggest-for-submission/{partialName}?limit={limit}";
            Taxon[] taxa = restTemplate.getForObject(requestUrl, CurrentTaxon[].class, partialName, limit);
            return Arrays.asList(taxa);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return new ArrayList();
            }
            throw new TaxonomyException(SERVICE_ERROR + ": " + e.getMessage());
        } catch (final ResourceAccessException e) {
            throw new TaxonomyException(SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public List<Taxon> suggestTaxa(String partialName, int limit) throws TaxonomyException {
        return suggestTaxa(partialName, false, limit);
    }

    @Override
    public List<Taxon> getTaxonByScientificName(String scientificName) throws TaxonomyException {
        try {
            String requestUrl = webServiceUrl + "/v1/taxon/scientific-name/{scientificName}";
            Taxon[] taxa = restTemplate.getForObject(requestUrl, CurrentFullTaxon[].class, scientificName);
            return Arrays.asList(taxa);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return new ArrayList();
            }
            throw new TaxonomyException(SERVICE_ERROR + ": " + e.getMessage());
        } catch (final ResourceAccessException e) {
            throw new TaxonomyException(SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public Taxon getTaxonById(long taxId) throws TaxonomyException {
        try {
            String requestUrl = webServiceUrl + "/v1/taxon/tax-id/{taxId}";
            CurrentFullTaxon currentFullTaxon = restTemplate.getForObject(requestUrl, CurrentFullTaxon.class, taxId);
            return currentFullTaxon;
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return new CurrentTaxon();
            }
            throw new TaxonomyException(SERVICE_ERROR + ": " + e.getMessage());
        } catch (final ResourceAccessException e) {
            throw new TaxonomyException(SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public boolean isScientificNameValid(String scientificName) throws TaxonomyException {
        return getTaxonByScientificName(scientificName).size() > 0;
    }

    @Override
    public boolean isTaxIdValid(long taxId) throws TaxonomyException {
        return !getTaxonById(taxId).getTaxId().equals(0L);
    }
}
