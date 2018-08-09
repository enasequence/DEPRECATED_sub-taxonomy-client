/*
 * Copyright 2018 EMBL - European Bioinformatics Institute
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package uk.ac.ebi.ena.taxonomy.client;

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

public abstract class TaxonomyClientAbstractTest
{
	protected TaxonomyClient taxonomyClient;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCheck_searchByValidScientificName()
	{
		List<Taxon> taxons;

		taxons = taxonomyClient.getTaxonByScientificName("Homo sapiens");
		assertEquals(1, taxons.size());

		taxons = taxonomyClient.getTaxonByScientificName("Aotus");
		assertEquals(2, taxons.size());
	}

	@Test
	public void testCheck_searchByValidTaxid()
	{
		Taxon taxons;

		taxons = taxonomyClient.getTaxonByTaxid(114498l);
		assertNotEquals(null, taxons);
	}

	@Test
	public void testCheck_searchByValidAnyName()
	{
		List<Taxon> taxons;

		taxons = taxonomyClient.getTaxonByAnyName("Human");
		assertEquals(1, taxons.size());

		taxons = taxonomyClient.getTaxonByAnyName("Aotus");
		assertEquals(2, taxons.size());
	}

	@Test
	public void testCheck_searchByValidCommonName()
	{
		List<Taxon> taxons;

		taxons = taxonomyClient.getTaxonByCommonName("Human");
		assertEquals(1, taxons.size());

		taxons = taxonomyClient.getTaxonByCommonName("night monkeys");
		assertEquals(1, taxons.size());
	}

	@Test
	public void testCheck_searchByInValidScientificName()
	{
		assertTrue(taxonomyClient.getTaxonByScientificName(null).isEmpty());
		assertTrue(taxonomyClient.getTaxonByScientificName("Aotu").isEmpty());
	}

	@Test
	public void testCheck_searchByInValidTaxid()
	{
		assertNull(taxonomyClient.getTaxonByTaxid(null));
		assertNull(taxonomyClient.getTaxonByTaxid(111l));
	}

	@Test
	public void testCheck_searchByInValidAnyName()
	{
		assertTrue(taxonomyClient.getTaxonByAnyName(null).isEmpty()) ;
		assertTrue(taxonomyClient.getTaxonByAnyName("Aos").isEmpty()) ;
	}

  @Test
  public void testCheck_searchByInValidCommonName() {
    assertTrue(taxonomyClient.getTaxonByCommonName(null).isEmpty());
    assertTrue(taxonomyClient.getTaxonByCommonName("homo").isEmpty());
  }

	@Test
	public void testCheck_submittableCommonName(){
		assertIsSubmittableTaxonAndNotNull(taxonomyClient.getSubmittableTaxonByCommonName("human"));
	}

	@Test
	public void testCheck_submittableScientificName(){
		assertIsSubmittableTaxonAndNotNull(taxonomyClient.getSubmittableTaxonByAnyName("Canis lupus familiaris"));
		assertIsSubmittableTaxonAndNotNull(taxonomyClient.getSubmittableTaxonByAnyName("Homo sapiens"));
	}

	@Test
	public void testCheck_submittableTaxid()
	{
		assertIsSubmittableTaxonAndNotNull(taxonomyClient.getSubmittableTaxonByTaxId(9606l));
	}

	@Test
	public void testCheck_submittableAnyName()
	{
		assertIsSubmittableTaxonAndNotNull(taxonomyClient.getSubmittableTaxonByAnyName("Human"));
	}

	@Test
	public void testCheck_notSubmittableCommonName(){

		SubmittableTaxonStatus submittableTaxonStatus;

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByCommonName(null).getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByCommonName("Huan").getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByCommonName("night monkeys").getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON, submittableTaxonStatus);
	}


	@Test
  public void testCheck_notSubmittableAnyName() {

    SubmittableTaxonStatus submittableTaxonStatus;

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByAnyName(null).getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByAnyName("hum").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByAnyName("night monkeys").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByAnyName("Aotus").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON, submittableTaxonStatus);
  }


  @Test
  public void testCheck_notSubmittableScientificName() {

    SubmittableTaxonStatus submittableTaxonStatus;

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByScientificName(null).getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByScientificName("human").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByScientificName("Aotus").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON, submittableTaxonStatus);

    submittableTaxonStatus =
        taxonomyClient.getSubmittableTaxonByScientificName("hum").getSubmittableTaxonStatus();
    assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);
  }




  	@Test
	public void testCheck_notSubmittableTaxid()	{

		SubmittableTaxonStatus submittableTaxonStatus;

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByTaxId(null).getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByTaxId(121l).getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.UNKNOWN_TAXON, submittableTaxonStatus);

		submittableTaxonStatus =
				taxonomyClient.getSubmittableTaxonByTaxId(114498l).getSubmittableTaxonStatus();
		assertEquals(SubmittableTaxonStatus.NOT_SUBMITTABLE_TAXON, submittableTaxonStatus);
	}



	@Test
	public void testCheck_suggestTaxa()
	{
		List<Taxon> taxons;

		taxons = taxonomyClient.suggestTaxa("hum");
		assertEquals(10, taxons.size());

		taxons = taxonomyClient.suggestTaxa("mmm");
		assertTrue(taxons.isEmpty());

		taxons = taxonomyClient.suggestTaxa("ohm");
		assertEquals(2, taxons.size());

		taxons = taxonomyClient.suggestTaxa(null);
		assertTrue(taxons.isEmpty());
	}

	@Test
	public void testCheck_suggestTaxawithLimit()
	{
		List<Taxon> taxons;

		taxons = taxonomyClient.suggestTaxa("hum", 5);
		assertEquals(5, taxons.size() );

		taxons = taxonomyClient.suggestTaxa("mmm", 5);
		assertTrue(taxons.isEmpty());

		taxons = taxonomyClient.suggestTaxa("ohm", 5);
		assertEquals(2, taxons.size());

		taxons = taxonomyClient.suggestTaxa(null, 5);
		assertTrue(taxons.isEmpty());

		taxons = taxonomyClient.suggestTaxa("", 5);
		assertTrue(taxons.isEmpty());
	}

	
	@Test
	public void testCheck_suggestTaxaWithMetagenome()
	{
		List<Taxon> taxon = taxonomyClient.suggestTaxa("fish",true,10);
		assertEquals(2, taxon.size());
		
	}


	@Test
	public void testCheck_ServerConnectionError() throws MalformedURLException {
		final URL NON_EXISTANT_SERVER_URL = new URL("http://www.invalid_domain.ac.uk/ena/data/taxonomy/v1/taxon/");
		expectedException.expect(TaxonomyException.class);
		getTaxons(NON_EXISTANT_SERVER_URL);
	}


	private static void assertIsSubmittableTaxonAndNotNull(SubmittableTaxon submittableTaxon){
		assertEquals(SubmittableTaxonStatus.SUBMITTABLE_TAXON ,
				submittableTaxon.getSubmittableTaxonStatus()) ;
		assertNotNull(submittableTaxon.getTaxon());
	}
}
