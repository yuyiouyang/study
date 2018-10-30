package com.syxt.base.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syxt.base.domain.SysRole;
import com.syxt.base.domain.SysUser;
import com.syxt.base.repository.SysRoleRepository;
import com.syxt.base.service.SysUserService;
import com.syxt.base.util.PageParam;
import com.syxt.base.util.Result;

@RestController
public class SysUserController {

	@Autowired
	private SysUserService sysUserServiceImpl;
	@Autowired
	private SysRoleRepository sysRoleRepository;

	/*@PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Enterprise getEnterpriseByName(@PathVariable String name) {
		return enterpriseServiceImpl.findByName(name);
	}*/

	//权限的校验
	//@PreAuthorize("hasAuthority('GUEST_GUEST')")
	/*@RequestMapping(path = "/current", method = RequestMethod.GET)
	public Result getCurrentEnterprise(Principal principal) {
		Enterprise account = sysUserServiceImpl.findByName(principal.getName());

		return new Result(200,"success",account);
	}*/

	/*@RequestMapping(path = "/current", method = RequestMethod.PUT)
	public Result saveCurrentAccount(Principal principal, @Valid @RequestBody Enterprise account) {
		String res = enterpriseServiceImpl.saveChanges(principal.getName(), account);

		if(res.equals("success")) return new Result(200,"success",res);
		return new Result(300,res,res);
	}*/

	/**
	 * 创建系统用户
	 * 默认分配GUEST权限
	 * @return
	 */
	@PreAuthorize("hasAuthority('ROOT_ROOT') or hasAuthority('ADMIN_ADMIN')")
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public Result createNewAccount(@Valid SysUser sysUser) {
		
		SysUser user = sysUserServiceImpl.create(sysUser);
		if(user==null) return new Result(300,"创建用户失败",user);
		return new Result(200,"success",user);
	}
	
	@PreAuthorize("hasAuthority('ROOT_ROOT')")
	@RequestMapping(path = "/listAllSysUsers/{page}/{size}", method = RequestMethod.GET)
	public Result listAllSysUsers(@PathVariable int page,@PathVariable int size) {
		PageParam pageParam = new PageParam(page, size);
		Page<SysUser> sysuers = sysUserServiceImpl.listAllSysUsers(pageParam);
		return new Result(200,"success",sysuers);
	}
	
	/**
	 * 新建或更新权限角色信息
	 * @param sysRole
	 */
	//@PreAuthorize("hasAuthority('GUEST_GUEST') or #oauth2.hasScope('server') ")
	@RequestMapping(path = "/createOrUpdateRole", method = RequestMethod.POST)
	public Result createOrUpdateRole(@Valid @RequestBody SysRole sysRole) {
		String res = sysUserServiceImpl.createOrUpdateRole(sysRole);
		if(res.equals("success")) return new Result(200,"success",res);
		return new Result(300,res,res);
	}
	
	/**
	 * 通过用户名和角色名
	 * 修改用户角色
	 * @param sysRole
	 */
	//@PreAuthorize("hasAuthority('GUEST_GUEST') or #oauth2.hasScope('server') ")
	@RequestMapping(path = "/updateSysUserRole", method = RequestMethod.POST)
	public Result updateSysUserRole(@RequestParam String username ,@RequestParam String[] roleNames ) {
		String res = sysUserServiceImpl.updateSysUserRole(username,roleNames);

		if(res.equals("success")) return new Result(200,"success",res);
		
		return new Result(300,res,res);
	}
	
	/**
	 * 查询所有角色信息
	 * @param sysRole
	 */
	//@PreAuthorize("hasAuthority('GUEST_GUEST') or #oauth2.hasScope('server') ")
	@RequestMapping(path = "/listAllSysRoles/{page}/{size}", method = RequestMethod.GET)
	public Result listAllRoles(@PathVariable int page,@PathVariable int size) {
		
		PageParam pageParam = new PageParam(page, size);
		Page<SysRole> sysRoles = sysUserServiceImpl.listAllSysRoles(pageParam);
		return new Result(200,"success",sysRoles);
	}
}
