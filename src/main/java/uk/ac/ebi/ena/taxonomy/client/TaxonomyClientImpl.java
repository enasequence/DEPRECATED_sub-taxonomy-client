package uk.ac.ebi.ena.taxonomy.client;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonErrorCode;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;
import uk.ac.ebi.ena.taxonomy.util.TaxonUtils;

public class TaxonomyClientImpl implements TaxonomyClient
{
	static Map<String, List<Taxon>> taxonScientificNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	static Map<Long, Taxon> taxonIdCache = Collections.synchronizedMap(new HashMap<Long, Taxon>());
	static Map<String, List<Taxon>> taxonCommonNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	static Map<String, List<Taxon>> taxonAnyNameCache = Collections.synchronizedMap(new HashMap<String, List<Taxon>>());
	private static final int DEFAULT_LIMIT = 10;

	@Override
	public List<Taxon> suggestTaxa(String partialName) throws TaxonomyException
	{
		return suggestTaxa(partialName, false, DEFAULT_LIMIT);
	}

	@Override
	public List<Taxon> suggestTaxa(String partialName, boolean metagenome) throws TaxonomyException
	{
		return suggestTaxa(partialName, metagenome, DEFAULT_LIMIT);
	}
	
	@Override
	public List<Taxon> suggestTaxa(String partialName, int limit) throws TaxonomyException
	{
		return suggestTaxa(partialName, false, limit);
	}

	@Override
	public List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit) throws TaxonomyException
	{
		String searchId = partialName + "?limit=" + limit;
		try
		{
			if (!TaxonomyUrl.suggestForSubmission.isValid(searchId))
			{
				throw new TaxonomyException(TaxonErrorCode.InvalidUrl.get(TaxonomyUrl.suggestForSubmission.get(searchId)));
			}
			URL url = new URL(TaxonomyUrl.suggestForSubmission.get(searchId).replaceAll(" ", "%20"));
			return TaxonUtils.getTaxon(url);
		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(partialName,TaxonomyUrl.suggestForSubmission.get(searchId))+ "\n" + e.getMessage());
		}
	}

	@Override
	public Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException
	{
		if (taxId == null)
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get("taxid", "taxid"));
		try
		{
			if (taxonIdCache.get(taxId) == null)
			{
				if (!TaxonomyUrl.taxid.isValid(taxId.toString()))
				{
					throw new TaxonomyException(TaxonErrorCode.InvalidUrl.get(TaxonomyUrl.taxid.get(taxId.toString())));
				}
				URL url = new URL(TaxonomyUrl.taxid.get(taxId.toString()).replaceAll(" ", "%20"));
				List<Taxon> taxon = TaxonUtils.getTaxon(url);
				taxonIdCache.put(taxId, taxon.size() > 0 ? taxon.get(0) : null);
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(taxId,	TaxonomyUrl.taxid.get(taxId.toString()))+ "\n"+ e.getMessage());
		}
		return taxonIdCache.get(taxId);
	}

	@Override
	public List<Taxon> getTaxonByScientificName(String scientificName)throws TaxonomyException
	{
		if (scientificName == null)
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get("scientificName", "scientificName"));
		try
		{
			if (taxonScientificNameCache.get(scientificName) == null)
			{
				if (!TaxonomyUrl.scientificName.isValid(scientificName))
				{
					throw new TaxonomyException(TaxonErrorCode.InvalidUrl.get(TaxonomyUrl.scientificName.get(scientificName)));
				}

				URL url = new URL(TaxonomyUrl.scientificName.get(scientificName).replaceAll(" ", "%20"));
				taxonScientificNameCache.put(scientificName, TaxonUtils.getTaxon(url));
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(scientificName,TaxonomyUrl.scientificName.get(scientificName))+ "\n" + e.getMessage());
		}
		return taxonScientificNameCache.get(scientificName);
	}

	@Override
	public List<Taxon> getTaxonByCommonName(String commonName)throws TaxonomyException
	{
		if (commonName == null)
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get("commonName", "commonName"));
		try
		{
			if (taxonCommonNameCache.get(commonName) == null)
			{
				if (!TaxonomyUrl.commonName.isValid(commonName))
				{
					throw new TaxonomyException(TaxonErrorCode.InvalidUrl.get(TaxonomyUrl.commonName.get(commonName)));
				}
				URL url = new URL(TaxonomyUrl.commonName.get(commonName).replaceAll(" ", "%20"));
				taxonCommonNameCache.put(commonName, TaxonUtils.getTaxon(url));
			}
		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(commonName, TaxonomyUrl.commonName.get(commonName))+ "\n"+ e.getMessage());
		}
		return taxonCommonNameCache.get(commonName);
	}

	@Override
	public List<Taxon> getTaxonByAnyName(String anyName)throws TaxonomyException
	{
		if (anyName == null)
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get("anyName", "anyName"));
		try
		{
			if (taxonAnyNameCache.get(anyName) == null)
			{
				if (!TaxonomyUrl.anyName.isValid(anyName))
				{
					throw new TaxonomyException(TaxonErrorCode.InvalidUrl.get(TaxonomyUrl.anyName.get(anyName)));
				}
				URL url = new URL(TaxonomyUrl.anyName.get(anyName).replaceAll(" ", "%20"));
				taxonAnyNameCache.put(anyName, TaxonUtils.getTaxon(url));
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(anyName, TaxonomyUrl.commonName.get(anyName))+ "\n"+ e.getMessage());
		}
		return taxonAnyNameCache.get(anyName);
	}

	@Override
	public Taxon getSubmittableTaxonByScientificName(String scientificName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByScientificName(scientificName),scientificName);

	}

	@Override
	public Taxon getSubmittableTaxonByAnyName(String anyName)throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByAnyName(anyName), anyName);

	}

	@Override
	public Taxon getSubmittableTaxonByCommonName(String commonName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByCommonName(commonName), commonName);

	}

	@Override
	public Taxon getSubmittableTaxonByTaxId(Long taxId)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByTaxid(taxId) == null ? null: Arrays.asList(getTaxonByTaxid(taxId)), taxId.toString());
	}

	
}
