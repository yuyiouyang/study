package com.syxt.base.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
public class SysUser {

	/**
	 * 登陆鉴权用的 账号
	 */
	private String username;
	private String password;
	private String imageUrl;
	private Party party;//所属团体
	private Set<SysRole> sysRoles;

	@Id
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sys_user_role", joinColumns = {
			@JoinColumn(name = "user_username", referencedColumnName = "username") }, inverseJoinColumns = {
					@JoinColumn(name = "role_name", referencedColumnName = "name") })
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="party_id")
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	
	
}
