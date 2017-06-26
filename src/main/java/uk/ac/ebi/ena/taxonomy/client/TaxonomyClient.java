package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;
import java.util.List;
enum TaxonomyUrl
{
	scientificName("scientific-name"),
	taxid("tax-id"),
	anyName("any-name"),
	commonName("common-name"),
	suggestForSubmission("suggest-for-submission");

	private final String url = "http://www.ebi.ac.uk/ena/taxonomy/rest/%s/%s";
	private String searchName;
	private TaxonomyUrl(String searchName)
	{
		this.searchName = searchName;
	}
	public String get(String searchId)
	{
		return String.format(url, searchName, searchId);
	}

}

public interface TaxonomyClient
{
	List<Taxon> suggestTaxa(String partialName) throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, boolean metagenome) throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit) throws TaxonomyException;

	List<Taxon> suggestTaxa(String partialName, int limit) throws TaxonomyException;

	List<Taxon> getTaxonByScientificName(String scientificName) throws TaxonomyException;
 
	List<Taxon> getTaxonByCommonName(String commonName) throws TaxonomyException;

	List<Taxon> getTaxonByAnyName(String anyName) throws TaxonomyException;

	Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException;

	SubmittableTaxon getSubmittableTaxonByTaxId(Long taxId) throws TaxonomyException;

	SubmittableTaxon getSubmittableTaxonByScientificName(String scientificName) throws TaxonomyException;

	SubmittableTaxon getSubmittableTaxonByAnyName(String anyName) throws TaxonomyException;

	SubmittableTaxon getSubmittableTaxonByCommonName(String commonName) throws TaxonomyException;
	
	boolean isMetagenomic(Taxon taxon) throws TaxonomyException;
	
	boolean isTaxIdValid(Long taxId) throws TaxonomyException;
	
	boolean isScientificNameValid(String scientificName) throws TaxonomyException;

}
