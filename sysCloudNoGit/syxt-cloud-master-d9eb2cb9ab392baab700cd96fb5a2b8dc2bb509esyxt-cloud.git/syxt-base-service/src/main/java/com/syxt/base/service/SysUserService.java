package com.syxt.base.service;

import org.springframework.data.domain.Page;

import com.syxt.base.domain.SysRole;
import com.syxt.base.domain.SysUser;
import com.syxt.base.util.PageParam;

public interface SysUserService {

	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param user
	 * @return created sysUser
	 */
	SysUser create(SysUser sysUser);
	
	/**
	 * 保存系统角色信息
	 * @param sysRole
	 * @return
	 */
	String createOrUpdateRole(SysRole sysRole);

	/**
	 * 更改用户角色
	 * @param username
	 * @param roleName
	 * @return
	 */
	String updateSysUserRole(String username, String[] roleName);
	
	/**
	 * 
	 * @param pageParam
	 * @param name
	 * @return
	 */
	Page<SysUser> listAllSysUsers(PageParam pageParam);
	
	
	Page<SysRole> listAllSysRoles(PageParam pageParam);
}
