package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

public interface TaxonomyClient {

  List<Taxon> suggestTaxa(String partialName);

}
