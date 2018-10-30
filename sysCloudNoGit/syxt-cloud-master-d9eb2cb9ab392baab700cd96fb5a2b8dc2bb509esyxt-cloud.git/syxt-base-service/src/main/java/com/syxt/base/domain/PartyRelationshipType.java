package com.syxt.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 团体关联关系类型 上下级、客户关系、监管关系等等
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "party_relationship_type")
public class PartyRelationshipType {
	private String partyTypeId;
	private String description;

	@Id
	@Column(name = "party_type_id")
	public String getPartyTypeId() {
		return partyTypeId;
	}

	public void setPartyTypeId(String partyTypeId) {
		this.partyTypeId = partyTypeId;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
