package com.syxt.base.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 团体关联关系
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "party_relationship")
public class PartyRelationship implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Party partyFrom;
	private Party partyTo;
	private PartyRelationshipType partyRelationType;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="party_id_from", referencedColumnName="party_id")
	public Party getPartyFrom() {
		return partyFrom;
	}

	public void setPartyFrom(Party partyFrom) {
		this.partyFrom = partyFrom;
	}

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="party_id_to", referencedColumnName="party_id")
	public Party getPartyTo() {
		return partyTo;
	}

	public void setPartyTo(Party partyTo) {
		this.partyTo = partyTo;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "party_reletionship_type_id")
	public PartyRelationshipType getPartyRelationType() {
		return partyRelationType;
	}

	public void setPartyRelationType(PartyRelationshipType partyRelationType) {
		this.partyRelationType = partyRelationType;
	}

}
