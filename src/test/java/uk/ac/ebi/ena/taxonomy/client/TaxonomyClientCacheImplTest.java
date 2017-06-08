package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TaxonomyClientCacheImplTest extends TaxonomyClientAbstractTest
{
	@Before
	public void setUp()
	{
		taxonomyClient = new TaxonomyClientCacheImpl();
	}
}
