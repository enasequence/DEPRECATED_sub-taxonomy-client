package uk.ac.ebi.ena.taxonomy.client.model;

public class CurrentTaxon implements Taxon {

    private Long taxId = 0L;
    private String scientificName;
    private String commonName;
    private String displayName;

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getScientificName() {
        return scientificName;
    }

    @Override
    public String getName() {
        return displayName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentTaxon taxon = (CurrentTaxon) o;

        if (taxId != null ? !taxId.equals(taxon.taxId) : taxon.taxId != null) return false;
        if (scientificName != null ? !scientificName.equals(taxon.scientificName) : taxon.scientificName != null)
            return false;
        if (commonName != null ? !commonName.equals(taxon.commonName) : taxon.commonName != null) return false;
        if (displayName != null ? !displayName.equals(taxon.displayName) : taxon.displayName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxId != null ? taxId.hashCode() : 0;
        result = 31 * result + (scientificName != null ? scientificName.hashCode() : 0);
        result = 31 * result + (commonName != null ? commonName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrentTaxon{" +
                "taxId=" + taxId +
                ", scientificName='" + scientificName + '\'' +
                ", commonName='" + commonName + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
