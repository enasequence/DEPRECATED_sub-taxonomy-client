package uk.ac.ebi.ena.taxonomy.client;

import uk.ac.ebi.ena.taxonomy.client.model.LegacyTaxon;
import uk.ac.ebi.ena.taxonomy.client.model.Taxon;

import java.util.List;

public class TaxonomyClientExample {

   TaxonomyClient taxonomyClient;

  public TaxonomyClientExample(String serviceUrl) {
    taxonomyClient = new TaxonomyClientCurrentImpl(serviceUrl);
  }

  public static void main(final String[] args) {
    if (args.length != 2) {
      System.out.println("Parameters: serviceUrl query");
    } else {
      final String serviceUrl = args[0];
      final String partialName = args[1];
      TaxonomyClientExample taxonomyClientExample = new TaxonomyClientExample(serviceUrl);
      taxonomyClientExample.query(partialName);
    }
  }

  private void query(String partialName) {

    try {
      final List<Taxon> taxonSuggestionList = taxonomyClient.suggestTaxa(partialName);
      outputList(taxonSuggestionList);
    } catch (final TaxonomyException ex) {
      System.out.println("Query failed with message: " + ex.getMessage());
    }
  }

  private void outputList(List<Taxon> taxonSuggestionList) {
    StringBuilder sb = new StringBuilder();
    sb.append("Tax ID");
    sb.append('\t');
    sb.append("Common Name");
    sb.append('\t');
    sb.append("Scientific Name");
    sb.append('\n');
    for (Taxon taxon : taxonSuggestionList)
    {
      sb.append(taxon.getTaxId());
      sb.append('\t');
      sb.append(taxon.getCommonName());
      sb.append('\t');
      sb.append(taxon.getScientificName());
      sb.append('\n');
    }
    System.out.println(sb.toString());
  }

}
