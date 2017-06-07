package uk.ac.ebi.ena.taxonomy.client;


import org.junit.Before;

public class TaxonomyClientImplTest extends TaxonomyClientBaseTest
{
	@Before
	public void setUp()
	{
		taxonomyClient = new TaxonomyClientImpl();
	}

}
