package uk.ac.ebi.ena.taxonomy.taxon;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxonFactory
{
	public Taxon createTaxon()
	{
		return new Taxon();
	}

	public Taxon createTaxon(JSONObject jsonTaxonObject) throws JSONException
	{
		Taxon taxon = new Taxon();
		if (jsonTaxonObject != null)
		{
			taxon.setTaxId(jsonTaxonObject.has("taxId") ? jsonTaxonObject.getLong("taxId") : null);
			taxon.setScientificName(jsonTaxonObject.has("scientificName") ? jsonTaxonObject.getString("scientificName") : null);
			taxon.setCommonName(jsonTaxonObject.has("commonName") ? jsonTaxonObject.getString("commonName") : null);
			taxon.setFormalName(jsonTaxonObject.has("formalName") && jsonTaxonObject.getBoolean("formalName"));
			taxon.setRank(jsonTaxonObject.has("rank") ? jsonTaxonObject.getString("rank") : null);
			taxon.setDivision(jsonTaxonObject.has("division") ? jsonTaxonObject.getString("division") : null);
			taxon.setGeneticCode(jsonTaxonObject.has("geneticCode") ? jsonTaxonObject.getInt("geneticCode") : null);
			taxon.setMitochondrialGeneticCode(jsonTaxonObject.has("mitochondrialGeneticCode") ? jsonTaxonObject.getInt("mitochondrialGeneticCode") : null);
			taxon.setPlastIdGeneticCode(jsonTaxonObject.has("plastIdGeneticCode") ? jsonTaxonObject.getInt("plastIdGeneticCode") : null);
			taxon.setLineage(jsonTaxonObject.has("lineage") ? jsonTaxonObject.getString("lineage") : null);
			taxon.setSubmittable(jsonTaxonObject.has("submittable") && jsonTaxonObject.getBoolean("submittable"));
		}
		return taxon;
	}
}
