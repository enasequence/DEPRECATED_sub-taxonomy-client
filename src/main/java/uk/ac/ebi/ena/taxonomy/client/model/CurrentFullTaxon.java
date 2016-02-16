package uk.ac.ebi.ena.taxonomy.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CurrentFullTaxon extends CurrentTaxon implements Taxon {

    private String formalName;
    private String rank;
    private String division;
    private String lineage;
    private String geneticCode;
    private String mitochondrialGeneticCode;
    private String plastIdGeneticCode;

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getLineage() {
        return lineage;
    }

    public void setLineage(String lineage) {
        this.lineage = lineage;
    }

    public String getGeneticCode() {
        return geneticCode;
    }

    public void setGeneticCode(String geneticCode) {
        this.geneticCode = geneticCode;
    }

    public String getMitochondrialGeneticCode() {
        return mitochondrialGeneticCode;
    }

    public void setMitochondrialGeneticCode(String mitochondrialGeneticCode) {
        this.mitochondrialGeneticCode = mitochondrialGeneticCode;
    }

    public String getPlastIdGeneticCode() {
        return plastIdGeneticCode;
    }

    public void setPlastIdGeneticCode(String plastIdGeneticCode) {
        this.plastIdGeneticCode = plastIdGeneticCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CurrentFullTaxon that = (CurrentFullTaxon) o;

        if (formalName != null ? !formalName.equals(that.formalName) : that.formalName != null) {
            return false;
        }
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) {
            return false;
        }
        if (division != null ? !division.equals(that.division) : that.division != null) {
            return false;
        }
        if (lineage != null ? !lineage.equals(that.lineage) : that.lineage != null) {
            return false;
        }
        if (geneticCode != null ? !geneticCode.equals(that.geneticCode) : that.geneticCode != null) {
            return false;
        }
        if (mitochondrialGeneticCode != null ? !mitochondrialGeneticCode.equals(that.mitochondrialGeneticCode) : that.mitochondrialGeneticCode != null) {
            return false;
        }
        if (plastIdGeneticCode != null ? !plastIdGeneticCode.equals(that.plastIdGeneticCode) : that.plastIdGeneticCode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (formalName != null ? formalName.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (division != null ? division.hashCode() : 0);
        result = 31 * result + (lineage != null ? lineage.hashCode() : 0);
        result = 31 * result + (geneticCode != null ? geneticCode.hashCode() : 0);
        result = 31 * result + (mitochondrialGeneticCode != null ? mitochondrialGeneticCode.hashCode() : 0);
        result = 31 * result + (plastIdGeneticCode != null ? plastIdGeneticCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrentFullTaxon{" +
                "formalName='" + formalName + '\'' +
                ", rank='" + rank + '\'' +
                ", division='" + division + '\'' +
                ", lineage='" + lineage + '\'' +
                ", geneticCode='" + geneticCode + '\'' +
                ", mitochondrialGeneticCode='" + mitochondrialGeneticCode + '\'' +
                ", plastIdGeneticCode='" + plastIdGeneticCode + '\'' +
                "} " + super.toString();
    }

}
