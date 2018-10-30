package com.syxt.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 团体类型 企业、政府、农户等
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "party_type")
public class PartyType {
	private String partyTypeId;
	private String partyTypeName;
	private String description;

	@Id
	@Column(name="party_type_id")
	public String getPartyTypeId() {
		return partyTypeId;
	}

	public void setPartyTypeId(String partyTypeId) {
		this.partyTypeId = partyTypeId;
	}

	@Column(name="party_type_name")
	public String getPartyTypeName() {
		return partyTypeName;
	}

	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
