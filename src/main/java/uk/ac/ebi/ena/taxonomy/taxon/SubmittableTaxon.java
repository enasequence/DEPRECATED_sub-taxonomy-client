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
