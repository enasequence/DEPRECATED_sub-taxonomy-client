package uk.ac.ebi.ena.taxonomy.client.model;

public class CurrentTaxon implements Taxon {

    private Long taxId = 0L;
    private String scientificName;
    private String commonName;
    private String displayName;
    private boolean submittable;

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
    public boolean isSubmittable() {
        return submittable;
    }

    public void setSubmittable(boolean submittable) {
        this.submittable = submittable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrentTaxon that = (CurrentTaxon) o;

        if (submittable != that.submittable) {
            return false;
        }
        if (taxId != null ? !taxId.equals(that.taxId) : that.taxId != null) {
            return false;
        }
        if (scientificName != null ? !scientificName.equals(that.scientificName) : that.scientificName != null) {
            return false;
        }
        if (commonName != null ? !commonName.equals(that.commonName) : that.commonName != null) {
            return false;
        }
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxId != null ? taxId.hashCode() : 0;
        result = 31 * result + (scientificName != null ? scientificName.hashCode() : 0);
        result = 31 * result + (commonName != null ? commonName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (submittable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrentTaxon{" +
                "taxId=" + taxId +
                ", scientificName='" + scientificName + '\'' +
                ", commonName='" + commonName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", submittable=" + submittable +
                '}';
    }
}
