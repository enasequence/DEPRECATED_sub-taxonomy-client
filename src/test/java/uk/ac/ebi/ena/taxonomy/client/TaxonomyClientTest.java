package uk.ac.ebi.ena.taxonomy.client;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

public class TaxonomyClientTest
{
	TaxonomyClient taxonomyClient;
	public ExpectedException expectedThrown = ExpectedException.none();

	@Before
	public void setUp()
	{
		taxonomyClient = new TaxonomyClientImpl();

	}

	@Test
	public void testCheck_searchByValidScientificName()
	{
		List<Taxon> taxon = taxonomyClient.getTaxonByScientificName("Homo sapiens");
		assertEquals(1, taxon.size());
		taxon = taxonomyClient.getTaxonByScientificName("Aotus");
		assertEquals(2, taxon.size());
	}

	@Test
	public void testCheck_searchByValidTaxid()
	{
		Taxon taxon = taxonomyClient.getTaxonByTaxid(114498l);
		assertNotEquals(null, taxon);
	}

	@Test
	public void testCheck_searchByValidAnyName()
	{
		List<Taxon> taxon = taxonomyClient.getTaxonByAnyName("Human");
		assertEquals(1, taxon.size());
		taxon = taxonomyClient.getTaxonByAnyName("Aotus");
		assertEquals(2, taxon.size());
	}

	@Test
	public void testCheck_searchByValidCommonName()
	{
		List<Taxon> taxon = taxonomyClient.getTaxonByCommonName("Human");
		assertEquals(1, taxon.size());
		taxon = taxonomyClient.getTaxonByCommonName("night monkeys");
		assertEquals(1, taxon.size());
	}

	@Test
	public void testCheck_searchByInValidScientificName()
	{
		try
		{
			taxonomyClient.getTaxonByScientificName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"scientificName is null or empty to search taxon by scientificName");
		}
		try
		{
			taxonomyClient.getTaxonByScientificName("Aotu");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by scientificName : Aotu (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/scientific-name/Aotu)");
		}

	}

	@Test
	public void testCheck_searchByInValidTaxid()
	{
		try
		{
			taxonomyClient.getTaxonByTaxid(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"taxid is null or empty to search taxon by taxid");
		}
		try
		{
			taxonomyClient.getTaxonByTaxid(111l);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by taxid : 111 (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/tax-id/111)");
		}

	}

	@Test
	public void testCheck_searchByInValidAnyName()
	{
		try
		{
			taxonomyClient.getTaxonByAnyName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"anyName is null or empty to search taxon by anyName");
		}
		try
		{
			taxonomyClient.getTaxonByAnyName("Aos");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by anyName : Aos (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/common-name/Aos)");
		}

	}

	@Test
	public void testCheck_searchByInValidCommonName()
	{
		try
		{
			taxonomyClient.getTaxonByCommonName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"commonName is null or empty to search taxon by commonName");
		}
		try
		{
			taxonomyClient.getTaxonByCommonName("homo");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by commonName : homo (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/common-name/homo)");
		}
	}

	@Test
	public void testCheck_submittableCommonName()
	{
		Taxon taxon = taxonomyClient.getSubmittableTaxonByCommonName("human");
		assertNotEquals(null, taxon);
	}

	@Test
	public void testCheck_submittableScientificName()
	{
		Taxon taxon = taxonomyClient.getSubmittableTaxonByScientificName("Homo sapiens");
		assertNotEquals(null, taxon);
	}

	@Test
	public void testCheck_submittableTaxid()
	{
		Taxon taxon = taxonomyClient.getSubmittableTaxonByTaxId(9606l);
		assertNotEquals(null, taxon);
	}

	@Test
	public void testCheck_submittableAnyName()
	{
		Taxon taxon = taxonomyClient.getSubmittableTaxonByAnyName("Human");
		assertNotEquals(null, taxon);
	}

	@Test
	public void testCheck_notSubmittableCommonName()
	{
		try
		{
			taxonomyClient.getSubmittableTaxonByCommonName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"commonName is null or empty to search taxon by commonName");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByCommonName("Huan");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by commonName : Huan (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/common-name/Huan)");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByCommonName("night monkeys");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"Not Submittable commonName: night monkeys");
		}
	}

	@Test
	public void testCheck_notSubmittableScientificName()
	{
		try
		{
			taxonomyClient.getSubmittableTaxonByScientificName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"scientificName is null or empty to search taxon by scientificName");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByScientificName("human");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by scientificName : human (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/scientific-name/human)");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByScientificName("Aotus");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"Not Submittable scientificName: Aotus");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByScientificName("hum");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by scientificName : hum (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/scientific-name/hum)");
		}
	}

	@Test
	public void testCheck_notSubmittableTaxid()
	{
		try
		{
			taxonomyClient.getSubmittableTaxonByTaxId(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"taxid is null or empty to search taxon by taxid");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByTaxId(121l);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by taxid : 121 (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/tax-id/121)");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByTaxId(114498l);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(), "Not Submittable taxid: 114498");
		}
	}

	@Test
	public void testCheck_notSubmittableAnyName()
	{
		try
		{
			taxonomyClient.getSubmittableTaxonByAnyName(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"anyName is null or empty to search taxon by anyName");
		}

		try
		{
			taxonomyClient.getSubmittableTaxonByAnyName("night monkeys");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),"Not Submittable anyName: night monkeys");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByAnyName("Aotus");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(), "Not Submittable anyName: Aotus");
		}
		try
		{
			taxonomyClient.getSubmittableTaxonByAnyName("hum");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by anyName : hum (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/common-name/hum)");
		}

	}

	@Test
	public void testCheck_suggestTaxa()
	{
		List<Taxon> taxon = taxonomyClient.suggestTaxa("hum");
		assertEquals(10, taxon.size());
		try
		{
			taxon = taxonomyClient.suggestTaxa("mmm");
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by suggestForSubmission : mmm (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/suggest-for-submission/mmm?limit=10)");
		}
		taxon = taxonomyClient.suggestTaxa("ohm");
		assertEquals(2, taxon.size());
		try
		{
			taxon = taxonomyClient.suggestTaxa(null);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by suggestForSubmission : null (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/suggest-for-submission/null?limit=10)");
		}
	}

	@Test
	public void testCheck_suggestTaxawithLimit()
	{
		List<Taxon> taxon = taxonomyClient.suggestTaxa("hum", 5);
		assertEquals(5, taxon.size());
		try
		{
			taxonomyClient.suggestTaxa("mmm", 5);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by suggestForSubmission : mmm (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/suggest-for-submission/mmm?limit=5)");
		}
		taxon = taxonomyClient.suggestTaxa("ohm", 5);
		assertEquals(2, taxon.size());
		try
		{
			taxon = taxonomyClient.suggestTaxa(null, 5);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by suggestForSubmission : null (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/suggest-for-submission/null?limit=5)");
		}
		try
		{
			taxon = taxonomyClient.suggestTaxa("", 5);
		} catch (TaxonomyException e)
		{
			assertEquals(e.getMessage(),
					     "Failed to search taxon by suggestForSubmission :  (http://www.ebi.ac.uk/ena/data/taxonomy/v1/taxon/suggest-for-submission/?limit=5)");
		}
	}
	
	@Test
	public void testCheck_suggestTaxawithMetagenome()
	{
		List<Taxon> taxon = taxonomyClient.suggestTaxa("fish",true,10);
		assertEquals(2, taxon.size());
		
	}

}
