package uk.ac.ebi.ena.taxonomy.taxon;



public class SubmittableTaxon
{
	public enum SubmittableTaxonStatus {
		SUBMITTABLE_TAXON,
		UNKNOWN_TAXON,
		NOT_SUBMITTABLE_TAXON,
		AMBIGUOUS_TAXON
	}

	public SubmittableTaxon(SubmittableTaxonStatus submittableTaxonStatus, Taxon taxon) {
		this.submittableTaxonStatus = submittableTaxonStatus;
		this.taxon = taxon;
	}

	private SubmittableTaxonStatus submittableTaxonStatus;
	private Taxon taxon;

	public SubmittableTaxonStatus getSubmittableTaxonStatus() {
		return submittableTaxonStatus;
	}

	public Taxon getTaxon() {
		return taxon;
	}


}
