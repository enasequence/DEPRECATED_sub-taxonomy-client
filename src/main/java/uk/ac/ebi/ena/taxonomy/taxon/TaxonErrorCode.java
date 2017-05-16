package uk.ac.ebi.ena.taxonomy.taxon;

import java.text.MessageFormat;

public enum TaxonErrorCode
{
	InvalidUrl("Invalid Taxon Search Url: %s"),
	SearchFailure("Failed to search taxon by %s : %s"),
	UnknownOrganism("Uknown Organism: %s"),
	AmbiguousOrganism("Ambiguous Organism: %s"),
	NotSubmittableOrganism("Not Submittable Organism: %s"),
	NullableSearchId("%s is null to search taxon by %s");
	
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