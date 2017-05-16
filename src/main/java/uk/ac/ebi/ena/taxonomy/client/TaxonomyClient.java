package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

enum TaxonomyUrl
{
	scientificName("scientific-name"),
	taxid("tax-id"),
	anyName("any-name"),
	commonName("common-name"),
	suggestForSubmission("suggest-for-submission");

	private final String url = "http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/%s/%s";
	private String searchName;

	private TaxonomyUrl(String searchName)
	{
		this.searchName = searchName;
	}

	public String get(String searchId)
	{
		return String.format(url, searchName, searchId);
	}

	public boolean isValid(String searchId) throws IOException
	{
		URL url = new URL(get(searchId).replaceAll(" ", "%20"));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		return con.getResponseCode() >= 400;
	}

}

public interface TaxonomyClient
{

	List<Taxon> suggestTaxa(String partialName) throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, boolean metagenome)
			throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit)
			throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, int limit)
			throws TaxonomyException;

	List<Taxon> getTaxonByScientificName(String scientificName)
			throws TaxonomyException;

	List<Taxon> getTaxonByCommonName(String commonName)
			throws TaxonomyException;

	List<Taxon> getTaxonByAnyName(String commonName) throws TaxonomyException;

	Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException;

	Taxon getSubmittableTaxonByTaxId(Long taxId) throws TaxonomyException;

	Taxon getSubmittableTaxonByScientificName(String scientificName)
			throws TaxonomyException;

	Taxon getSubmittableTaxonByAnyName(String anyName) throws TaxonomyException;

	Taxon getSubmittableTaxonByCommonName(String commonName)
			throws TaxonomyException;

}
