package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

public interface TaxonomyClient {

  List<Taxon> suggestTaxa(String partialName) throws TaxonomyException;
  
  List<Taxon> suggestTaxa(String partialName, boolean metagenome) throws TaxonomyException;
  
  List<Taxon> suggestTaxa(String partialName, boolean metagenome, int limit) throws TaxonomyException;
  
  List<Taxon> suggestTaxa(String partialName, int limit) throws TaxonomyException;

  List<Taxon> getTaxonByScientificName(String scientificName) throws TaxonomyException;

  Taxon getTaxonById(long taxId) throws TaxonomyException;

  boolean isScientificNameValid(String scientificName) throws TaxonomyException;
  
  boolean isTaxIdValid(long taxId) throws TaxonomyException;
}
