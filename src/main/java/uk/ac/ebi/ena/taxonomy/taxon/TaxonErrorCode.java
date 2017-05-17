package uk.ac.ebi.ena.taxonomy.taxon;

import java.text.MessageFormat;

public enum TaxonErrorCode
{
	SearchFailure("Failed to search taxon by {0} : {1} ({2})"),
	UnknownTaxon("Uknown {0}: {1}"),
	AmbiguousTaxon("Ambiguous {0}: {1}"),
	NotSubmittableTaxon("Not Submittable {0}: {1}"),
	NullableSearchId("{0} is null or empty to search taxon by {1}");
	
	String message;
	private TaxonErrorCode(String message)
	{
       this.message= message;
	}
	
	public String get(Object... params )
	{
		return MessageFormat.format(message,params);
	}
	
	
}