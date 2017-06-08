package uk.ac.ebi.ena.taxonomy.client;


import org.junit.Before;

public class TaxonomyClientImplTest extends TaxonomyClientAbstractTest
{
	@Before
	public void setUp()
	{
		taxonomyClient = new TaxonomyClientImpl();
	}

}
