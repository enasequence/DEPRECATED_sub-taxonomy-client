package uk.ac.ebi.ena.taxonomy.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonErrorCode;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonFactory;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

public class TaxonUtils
{
    
	public static Taxon getSubmittableTaxon(List<Taxon> taxons, String name) 
	{
		List<Taxon> submittableTaxons = new ArrayList<Taxon>();

		if (taxons == null || taxons.size() == 0)
			throw new TaxonomyException(TaxonErrorCode.UnknownOrganism.get(name));
		for (Taxon taxon : taxons)
		{
			if (taxon.isSubmittable())
				submittableTaxons.add(taxon);
		}
		if (submittableTaxons.size() < 1)
			throw new TaxonomyException(TaxonErrorCode.NotSubmittableOrganism.get(name));
		if (submittableTaxons.size() > 1)
			throw new TaxonomyException(TaxonErrorCode.AmbiguousOrganism.get(name));
		if (submittableTaxons.size() == 1)
			return submittableTaxons.get(0);

		return null;
	}

	public static List<Taxon> getTaxon(URL url) throws IOException, JSONException
	{
		List<Taxon> taxons = new ArrayList<Taxon>();
		TaxonFactory taxonFactory =new TaxonFactory();
		JsonUtils jsonUtils = new JsonUtils();
		JSONArray jsonTaxonObject = jsonUtils.getJsonArray(url);
		if (jsonTaxonObject == null)
		{
			return taxons;
		}
		for (int i = 0; i < jsonTaxonObject.length(); i++)
			taxons.add(taxonFactory.createTaxon(jsonTaxonObject.getJSONObject(i)));

		return taxons;
	}
}
