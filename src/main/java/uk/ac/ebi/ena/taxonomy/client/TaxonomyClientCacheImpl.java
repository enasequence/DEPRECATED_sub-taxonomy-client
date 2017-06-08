package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxonomyClientCacheImpl implements TaxonomyClient
{
	private TaxonomyClient taxonomyClient ;
	private static Map<String, List<Taxon>> taxonScientificNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	private static Map<Long, Taxon> taxonIdCache = Collections.synchronizedMap(new HashMap<Long, Taxon>());
	private static Map<String, List<Taxon>> taxonCommonNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	private static Map<String, List<Taxon>> taxonAnyNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	private static final int DEFAULT_LIMIT = 10;

	public TaxonomyClientCacheImpl(TaxonomyClient taxonomyClient) {
		this.taxonomyClient = taxonomyClient;
	}
	public TaxonomyClientCacheImpl() {
		this.taxonomyClient = new TaxonomyClientCacheImpl(new TaxonomyClientImpl());
	}

	@Override
	public List<Taxon> suggestTaxa(String partialName) throws TaxonomyException
	{
		return taxonomyClient.suggestTaxa(partialName, false, DEFAULT_LIMIT);
	}

	@Override
	public List<Taxon> suggestTaxa(String partialName, boolean metagenome) throws TaxonomyException
	{
		return taxonomyClient.suggestTaxa(partialName, metagenome, DEFAULT_LIMIT);
	}

	@Override
	public List<Taxon> suggestTaxa(String partialName, int limit) throws TaxonomyException
	{
		return taxonomyClient.suggestTaxa(partialName, false, limit);
	}


	public List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit) throws TaxonomyException
	{
		return taxonomyClient.suggestTaxa(partialName, metagenome, limit);
	}

	@Override
	public Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException
	{
		return taxonIdCache.computeIfAbsent(taxId, taxonomyClient::getTaxonByTaxid);
	}

	@Override
	public List<Taxon> getTaxonByScientificName(String scientificName)throws TaxonomyException
	{
		return taxonScientificNameCache.computeIfAbsent(scientificName, taxonomyClient::getTaxonByScientificName);
	}

	@Override
	public List<Taxon> getTaxonByCommonName(String commonName)throws TaxonomyException
	{
		return taxonCommonNameCache.computeIfAbsent(commonName, taxonomyClient::getTaxonByCommonName);
	}

	@Override
	public List<Taxon> getTaxonByAnyName(String anyName)throws TaxonomyException
	{
		return taxonAnyNameCache.computeIfAbsent(anyName, taxonomyClient::getTaxonByAnyName);
	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByScientificName(String scientificName)	throws TaxonomyException
	{
		return taxonomyClient.getSubmittableTaxonByScientificName(scientificName);

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByAnyName(String anyName)throws TaxonomyException
	{
		return taxonomyClient.getSubmittableTaxonByAnyName(anyName);

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByCommonName(String commonName)	throws TaxonomyException
	{
		return taxonomyClient.getSubmittableTaxonByAnyName(commonName);

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByTaxId(Long taxId)	throws TaxonomyException
	{
		return taxonomyClient.getSubmittableTaxonByTaxId(taxId);
	}

	@Override
	public boolean isMetagenomic(Taxon taxon) throws TaxonomyException
	{
		if (taxon == null || taxon.getTaxId() == null)
			return false;
		if (taxon.getLineage() == null)
		{
			return getTaxonByTaxid(taxon.getTaxId()).isMetagenome();
		}
		return taxon.isMetagenome();

	}

	@Override
	public boolean isTaxIdValid(Long taxId) throws TaxonomyException
	{
		Taxon taxon = getTaxonByTaxid(taxId);
		return taxon != null;
	}

	@Override
	public boolean isScientificNameValid(String scientificName)
			throws TaxonomyException
	{

		List<Taxon> taxon = getTaxonByScientificName(scientificName);
		return !(taxon == null || taxon.isEmpty());

	}

}
