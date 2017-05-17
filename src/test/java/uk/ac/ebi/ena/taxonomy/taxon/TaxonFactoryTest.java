package uk.ac.ebi.ena.taxonomy.taxon;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class TaxonFactoryTest
{

	private TaxonFactory factory;

	@Before
	public void setUp() throws Exception
	{
		factory = new TaxonFactory();
	}

	@Test
	public void testCreateNullTaxon() throws JSONException
	{

		Taxon result = factory.createTaxon();
		assertNull(result.getTaxId());
		assertNull(result.getScientificName());
		assertNull(result.getCommonName());
		assertNull(result.getDivision());
		assertNull(result.getGeneticCode());
		assertNull(result.getMitochondrialGeneticCode());
		assertNull(result.getPlastIdGeneticCode());
		assertNull(result.getRank());
		assertNull(result.getLineage());
		assertEquals(0, result.getFamilyNames().size());
		assertFalse(result.isFormal());
	}

	@Test
	public void testCreateTaxon() throws JSONException
	{

		JSONObject taxonJasonObject = new JSONObject("{"
						                             + "\"taxId\": \"2759\","
						                             + "\"scientificName\": \"Felis catus\","
						                             + "\"commonName\": \"domestic cat\","
						                             + "\"formalName\": \"true\","
						                             + "\"rank\": \"superkingdom\","
						                             + "\"division\": \"INV\","
						                             + "\"lineage\": \"Eukaryota; Metazoa; Chordata; Craniata; Vertebrata; Euteleostomi; Mammalia;Eutheria; Laurasiatheria; Carnivora; Feliformia; Felidae; Felinae; Felis;\","
						                             + "\"geneticCode\": \"1\","
						                             + "\"mitochondrialGeneticCode\": \"1\","
						                             + "\"plastIdGeneticCode\": \"11\"" + "}");
		Taxon result = factory.createTaxon(taxonJasonObject);
		assertEquals(new Long(2759L), result.getTaxId());
		assertEquals("Felis catus", result.getScientificName());
		assertEquals("domestic cat", result.getCommonName());
		assertEquals("INV", result.getDivision());
		assertEquals(new Integer(1), result.getGeneticCode());
		assertEquals(new Integer(1), result.getMitochondrialGeneticCode());
		assertEquals(new Integer(11), result.getPlastIdGeneticCode());
		assertEquals("superkingdom", result.getRank());
		assertEquals(
				"Eukaryota; Metazoa; Chordata; Craniata; Vertebrata; Euteleostomi; Mammalia;Eutheria; Laurasiatheria; Carnivora; Feliformia; Felidae; Felinae; Felis;",
				result.getLineage());
		assertEquals(14, result.getFamilyNames().size());
		assertTrue(result.isFormal());
	}

	@Test
	public void testCreateTaxon_Lineage() throws JSONException
	{
		JSONObject taxonJasonObject = new JSONObject("{"
						                              + "\"taxId\": \"1\","
						                              + "\"scientificName\": \"name\","
						                              + "\"commonName\": \"common\","
						                              + "\"lineage\": \"Eukaryota; Metazoa; Chordata; Craniata; Vertebrata; Euteleostomi; Mammalia;Eutheria; Laurasiatheria; Carnivora; Feliformia; Felidae; Felinae; Felis;\","
						                              + "}");
		Taxon result = factory.createTaxon(taxonJasonObject);
		assertEquals(new Long(1L), result.getTaxId());
		assertEquals("name", result.getScientificName());
		assertEquals("common", result.getCommonName());
		assertEquals(
				"Eukaryota; Metazoa; Chordata; Craniata; Vertebrata; Euteleostomi; Mammalia;Eutheria; Laurasiatheria; Carnivora; Feliformia; Felidae; Felinae; Felis;",
				result.getLineage());
		assertEquals(14, result.getFamilyNames().size());
		assertNull(result.getDivision());
		assertNull(result.getGeneticCode());
		assertNull(result.getMitochondrialGeneticCode());
		assertNull(result.getRank());
	}

}
