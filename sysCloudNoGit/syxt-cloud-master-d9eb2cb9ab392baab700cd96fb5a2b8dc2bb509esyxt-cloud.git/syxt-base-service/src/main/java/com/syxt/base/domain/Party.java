package com.syxt.base.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 团体对象 团体指任何系统的使用方，可以是个人、企业、政府等等
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "party")
public class Party implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partyId;
	private String partyName;
	private String description;
	private PartyType partyType;

	@Id
	@Column(name="party_id")
	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	@Column(name="party_name")
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="party_type_id")
	public PartyType getPartyType() {
		return partyType;
	}

	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}

	
}
