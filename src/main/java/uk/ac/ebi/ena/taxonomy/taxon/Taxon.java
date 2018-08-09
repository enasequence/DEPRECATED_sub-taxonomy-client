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

import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Taxon implements Serializable
{

	private static final long serialVersionUID = -6077147991776457611L;
	private Long taxId = null;
	private String scientificName = null;
	private String commonName = null;
	private String division = null;
	private Integer geneticCode = null;
	private Integer mitochondrialGeneticCode = null;
	private Integer plastIdGeneticCode = null;
	private String rank = null;
	private Boolean formalName = false;
	private String lineage = null;
	private Boolean submittable = false;

	protected Taxon()
	{

	}

	public boolean isMetagenome()
	{
		return isChildOf("metagenomes");
	}

	public Long getTaxId()
	{
		return this.taxId;
	}

	public void setTaxId(Long taxId)
	{
		this.taxId = taxId;
	}

	public String getScientificName()
	{
		return this.scientificName;
	}

	public void setScientificName(String scientificName)
	{
		this.scientificName = scientificName;
	}

	public String getCommonName()
	{
		return this.commonName;
	}

	public void setCommonName(String genbankCommonName)
	{
		this.commonName = genbankCommonName;
	}

	public String getDivision()
	{
		return division;
	}

	public void setDivision(String division)
	{
		this.division = division;
	}

	public Integer getGeneticCode()
	{
		return geneticCode;
	}

	public void setGeneticCode(Integer geneticCode)
	{
		this.geneticCode = geneticCode;
	}

	public Integer getMitochondrialGeneticCode()
	{
		return mitochondrialGeneticCode;
	}

	public void setMitochondrialGeneticCode(Integer mitochondrialGeneticCode)
	{
		this.mitochondrialGeneticCode = mitochondrialGeneticCode;
	}

	public Integer getPlastIdGeneticCode()
	{
		return plastIdGeneticCode;
	}

	public void setPlastIdGeneticCode(Integer plastIdGeneticCode)
	{
		this.plastIdGeneticCode = plastIdGeneticCode;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public Boolean isFormal()
	{
		return formalName;
	}

	public void setFormalName(Boolean formalName)
	{
		this.formalName = formalName;
	}

	public void setLineage(String lineage)
	{
		this.lineage = lineage;
	}

	public String getLineage()
	{
		return lineage;
	}

	public Boolean isSubmittable()
	{
		return submittable;
	}

	public void setSubmittable(Boolean submittable)
	{
		this.submittable = submittable;
	}

	public List<String> getFamilyNames()
	{
		List<String> familyNames = new ArrayList<String>();
		if (this.lineage != null)
		{
			String[] names = this.lineage.split(";");
			for (String name : names)
			{
				if (!" ".equals(name))
					familyNames.add(name.trim());
			}
		}
		return familyNames;
	}

	public boolean isChildOf(String parentName)
	{
		if (parentName == null)
			return false;
		return getFamilyNames().contains(parentName.trim());
	}

	@Override
	public String toString()
	{
		final ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("taxId", taxId);
		builder.append("scientificName", scientificName);
		builder.append("commonName", commonName);
		builder.append("rank", rank);
		builder.append("geneticCode", geneticCode);
		builder.append("mitochondrialGeneticCode", mitochondrialGeneticCode);
		builder.append("division", division);
		builder.append("lineage", lineage);
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj != null && obj instanceof Taxon)
		{
			final Taxon other = (Taxon) obj;
			final EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.taxId, other.taxId);
			builder.append(this.scientificName, other.scientificName);
			builder.append(this.commonName, other.commonName);
			builder.append(this.rank, other.rank);
			builder.append(this.division, other.division);
			builder.append(this.geneticCode, other.geneticCode);
			builder.append(this.mitochondrialGeneticCode,other.mitochondrialGeneticCode);
			return builder.isEquals();
		} else
		{
			return false;
		}
	}

}
