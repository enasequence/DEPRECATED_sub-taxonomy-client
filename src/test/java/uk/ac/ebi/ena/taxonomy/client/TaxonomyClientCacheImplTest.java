package uk.ac.ebi.ena.taxonomy.client;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon;
import uk.ac.ebi.ena.taxonomy.taxon.SubmittableTaxon.SubmittableTaxonStatus;
import uk.ac.ebi.ena.taxonomy.taxon.Taxon;
import uk.ac.ebi.ena.taxonomy.taxon.TaxonomyException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static uk.ac.ebi.ena.taxonomy.util.TaxonUtils.getTaxons;

public class TaxonomyClientCacheImplTest extends TaxonomyClientBaseTest
{
	@Before
	public void setUp()
	{
		taxonomyClient = new TaxonomyClientCacheImpl();
	}
}
