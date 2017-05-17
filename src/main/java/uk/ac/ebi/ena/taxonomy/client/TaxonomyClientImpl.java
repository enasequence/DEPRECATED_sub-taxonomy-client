package uk.ac.ebi.ena.taxonomy.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
			if (partialName == null||partialName.isEmpty())
				throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get(TaxonomyUrl.suggestForSubmission.name(),TaxonomyUrl.suggestForSubmission.name()));
			
			URL url = new URL(TaxonomyUrl.suggestForSubmission.get(searchId).replaceAll(" ", "%20"));
			if(!metagenome)
			return TaxonUtils.getTaxon(url);
			
			return TaxonUtils.getTaxon(url).stream().filter(taxon->isMetagenomic(taxon)).collect(Collectors.toList());
		
		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(TaxonomyUrl.suggestForSubmission.name(),partialName,TaxonomyUrl.suggestForSubmission.get(searchId)));
		}
	}

	@Override
	public Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException
	{
		if (taxId == null)
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get(TaxonomyUrl.taxid.name(), TaxonomyUrl.taxid.name()));
		try
		{
			if (taxonIdCache.get(taxId) == null)
			{
				URL url = new URL(TaxonomyUrl.taxid.get(taxId.toString()).replaceAll(" ", "%20"));
				List<Taxon> taxon = TaxonUtils.getTaxon(url);
				taxonIdCache.put(taxId, taxon.size() > 0 ? taxon.get(0) : null);
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(TaxonomyUrl.taxid.name(),taxId,TaxonomyUrl.taxid.get(taxId.toString())));
		}
		return taxonIdCache.get(taxId);
	}

	@Override
	public List<Taxon> getTaxonByScientificName(String scientificName)throws TaxonomyException
	{
		if (scientificName == null||scientificName.isEmpty())
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get(TaxonomyUrl.scientificName.name(),TaxonomyUrl.scientificName.name()));
		try
		{
			if (taxonScientificNameCache.get(scientificName) == null)
			{
				URL url = new URL(TaxonomyUrl.scientificName.get(scientificName).replaceAll(" ", "%20"));
				taxonScientificNameCache.put(scientificName, TaxonUtils.getTaxon(url));
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(TaxonomyUrl.scientificName.name(),scientificName,TaxonomyUrl.scientificName.get(scientificName)));
		}
		return taxonScientificNameCache.get(scientificName);
	}

	@Override
	public List<Taxon> getTaxonByCommonName(String commonName)throws TaxonomyException
	{
		if (commonName == null||commonName.isEmpty())
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get(TaxonomyUrl.commonName.name(), TaxonomyUrl.commonName.name()));
		try
		{
			if (taxonCommonNameCache.get(commonName) == null)
			{
				URL url = new URL(TaxonomyUrl.commonName.get(commonName).replaceAll(" ", "%20"));
				taxonCommonNameCache.put(commonName, TaxonUtils.getTaxon(url));
			}
		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(TaxonomyUrl.commonName.name(),commonName, TaxonomyUrl.commonName.get(commonName)));
		}
		return taxonCommonNameCache.get(commonName);
	}

	@Override
	public List<Taxon> getTaxonByAnyName(String anyName)throws TaxonomyException
	{
		if (anyName == null||anyName.isEmpty())
			throw new TaxonomyException(TaxonErrorCode.NullableSearchId.get(TaxonomyUrl.anyName.name(), TaxonomyUrl.anyName.name()));
		try
		{
			if (taxonAnyNameCache.get(anyName) == null)
			{
				URL url = new URL(TaxonomyUrl.anyName.get(anyName).replaceAll(" ", "%20"));
				taxonAnyNameCache.put(anyName, TaxonUtils.getTaxon(url));
			}

		} catch (Exception e)
		{
			throw new TaxonomyException(TaxonErrorCode.SearchFailure.get(TaxonomyUrl.anyName.name(),anyName, TaxonomyUrl.commonName.get(anyName)));
		}
		return taxonAnyNameCache.get(anyName);
	}

	@Override
	public Taxon getSubmittableTaxonByScientificName(String scientificName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByScientificName(scientificName),scientificName,TaxonomyUrl.scientificName.name());

	}

	@Override
	public Taxon getSubmittableTaxonByAnyName(String anyName)throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByAnyName(anyName), anyName,TaxonomyUrl.anyName.name());

	}

	@Override
	public Taxon getSubmittableTaxonByCommonName(String commonName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByCommonName(commonName), commonName,TaxonomyUrl.commonName.name());

	}

	@Override
	public Taxon getSubmittableTaxonByTaxId(Long taxId)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByTaxid(taxId) == null ? null: Arrays.asList(getTaxonByTaxid(taxId)), taxId.toString(),TaxonomyUrl.taxid.name());
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
	

	
}
