package uk.ac.ebi.ena.taxonomy.util;

import org.json.JSONArray;

import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon;
import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonFactory;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus.AMBIGUOUS_TAXON;
import static uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON;
import static uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus.SUBMITTABLE_TAXON;
import static uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus.UNKNOWN_TAXON;


public class TaxonUtils
{

	public static SubmittableTaxon getSubmittableTaxon(List<Taxon> taxons)
	{
	    Taxon foundTaxon = null;
        SubmittableTaxonStatus status = getSubmittableTaxonStatus(taxons);
		if(SUBMITTABLE_TAXON == status){
            foundTaxon = taxons.stream().filter(Taxon::isSubmittable).findFirst().orElse(null);
		}
        return new SubmittableTaxon( status , foundTaxon);
	}


	public static SubmittableTaxonStatus getSubmittableTaxonStatus(List<Taxon> taxons)
	{
		List<Taxon> submittableTaxons = new ArrayList<>();

		if ((taxons == null) || (taxons.isEmpty())) {
            return UNKNOWN_TAXON;
        }

        taxons.forEach(taxon -> { if (taxon.isSubmittable()) submittableTaxons.add(taxon);});

        if (submittableTaxons.size() == 1)
			return SUBMITTABLE_TAXON ;
        else if (submittableTaxons.size() > 1)
            return AMBIGUOUS_TAXON;
		else return NOT_SUBMITTABLE_TAXON;
	}


	public static List<Taxon> getTaxons(URL url)
	{
		List<Taxon> taxons = new ArrayList<>();
        JsonUtils jsonUtils = new JsonUtils();
		JSONArray jsonTaxonObject ;
		try {
			jsonTaxonObject = jsonUtils.getJsonArray(url);
		} catch (IOException e) {
			throw new TaxonomyException(e);
		}
		if (jsonTaxonObject == null)
		{
			return taxons;
		}
		TaxonFactory taxonFactory =new TaxonFactory();
		for (int i = 0; i < jsonTaxonObject.length(); i++)
			taxons.add(taxonFactory.createTaxon(jsonTaxonObject.getJSONObject(i)));
		
		return taxons;
	}
}
