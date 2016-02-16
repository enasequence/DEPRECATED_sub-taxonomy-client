package uk.ac.ebi.ena.taxonomy.client.model;

public interface Taxon {

    Long getTaxId();

    String getCommonName();

    String getScientificName();

    String getName();

    boolean isSubmittable();
}
