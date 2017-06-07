package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;
import uk.ac.ebi.ena.taxonomy.util.TaxonUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class TaxonomyClientImpl implements TaxonomyClient
{
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
		if(partialName == null) return new ArrayList<>();
		String searchId = partialName + "?limit=" + limit;
		URL url;
		try {
			url = new URL(TaxonomyUrl.suggestForSubmission.get(searchId).replaceAll(" ", "%20"));
		} catch (MalformedURLException e) {
			throw new TaxonomyException(e);
		}
		if(!metagenome) {
			return TaxonUtils.getTaxons(url);
		} else {
			return TaxonUtils.getTaxons(url).stream().filter(this::isMetagenomic).collect(Collectors.toList());
		}
	}

	@Override
	public Taxon getTaxonByTaxid(Long taxId) throws TaxonomyException	{
		if (taxId == null)
			return null;
		try
		{
				URL url = new URL(TaxonomyUrl.taxid.get(taxId.toString()).replaceAll(" ", "%20"));
				List<Taxon> taxon = TaxonUtils.getTaxons(url);
				return !taxon.isEmpty() ? taxon.get(0) : null;
		} catch (Exception e)
		{
			throw new TaxonomyException(e);
		}
		
	}

	@Override
	public List<Taxon> getTaxonByScientificName(String scientificName)throws TaxonomyException
	{
		if (scientificName == null) new ArrayList<>();
		try
		{
				URL url = new URL(TaxonomyUrl.scientificName.get(scientificName).replaceAll(" ", "%20"));
				return TaxonUtils.getTaxons(url);
		} catch (Exception e)
		{
			throw new TaxonomyException(e);
		}
	}

	@Override
	public List<Taxon> getTaxonByCommonName(String commonName)throws TaxonomyException	{
		if (commonName == null||commonName.isEmpty()) return new ArrayList<>();
		try
		{
				URL url = new URL(TaxonomyUrl.commonName.get(commonName).replaceAll(" ", "%20"));
				return TaxonUtils.getTaxons(url);
			
		} catch (Exception e)
		{
			throw new TaxonomyException(e);
		}
	}

	@Override
	public List<Taxon> getTaxonByAnyName(String anyName)throws TaxonomyException
	{
		if (anyName == null||anyName.isEmpty())
			return new ArrayList<>();
		try
		{
				URL url = new URL(TaxonomyUrl.anyName.get(anyName).replaceAll(" ", "%20"));
				return TaxonUtils.getTaxons(url);
		} catch (Exception e)
		{
			throw new TaxonomyException(e);
		}
	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByScientificName(String scientificName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByScientificName(scientificName));

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByAnyName(String anyName)throws TaxonomyException
	{

		return TaxonUtils.getSubmittableTaxon(getTaxonByAnyName(anyName));

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByCommonName(String commonName)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByCommonName(commonName));

	}

	@Override
	public SubmittableTaxon getSubmittableTaxonByTaxId(Long taxId)	throws TaxonomyException
	{
		return TaxonUtils.getSubmittableTaxon(getTaxonByTaxid(taxId) == null ? null: Collections.singletonList(getTaxonByTaxid(taxId)));
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
