package com.syxt.base.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.syxt.base.client.AuthServiceClient;
import com.syxt.base.client.StatisticsServiceClient;
import com.syxt.base.domain.SysAuthority;
import com.syxt.base.domain.SysRole;
import com.syxt.base.domain.SysUser;
import com.syxt.base.repository.SysRoleRepository;
import com.syxt.base.repository.SysUserRepository;
import com.syxt.base.util.PageParam;

@Service
public class SysUserServiceImpl implements SysUserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private StatisticsServiceClient statisticsClient;

	@Autowired
	private AuthServiceClient authClient;
	
	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	@Autowired
	private SysUserRepository sysUserRepository;
	
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	/**
	 * 创建系统用户
	 * 保存用户权限信息
	 * 同时保存用户权限信息到鉴权服务器
	 * {@inheritDoc}
	 */
	@Override
	public SysUser create(SysUser sysUser) {

		SysUser existing = sysUserRepository.findOne(sysUser.getUsername());
		
		if(existing!=null) return null;
		
		Set<SysRole> roles = new HashSet<SysRole>();
		Set<SysAuthority> as = new HashSet<SysAuthority>();
		
		SysAuthority a = new SysAuthority();
		a.setName("GUEST_GUEST");
		a.setValue("GUEST_GUEST");
		as.add(a);
		
		SysRole role = new SysRole();
		role.setName("GUEST");
		role.setValue("GUEST");
		role.setSysAuthorities(as);
		
		roles.add(role);
		sysUser.setSysRoles(roles);
		
		SysUser toSave = new SysUser();
		toSave.setUsername(sysUser.getUsername());
		toSave.setSysRoles(sysUser.getSysRoles());
		toSave.setPassword(encoder.encode(sysUser.getPassword()));
		sysUserRepository.save(toSave);
		
		authClient.createUser(sysUser);
		
		/*Enterprise account = new Enterprise();
		account.setName(sysUser.getUsername());
		account.setLastSeen(new Date());
		//account.setSaving(saving);

		enterpriseRepository.save(account);

		log.info("new account has been created: " + account.getName());*/

		return sysUser;
	}

	@Override
	public String createOrUpdateRole(SysRole sysRole) {
		SysRole role = sysRoleRepository.findOne(sysRole.getName());
		//如果没有这个角色，创建
		if(role==null){
			sysRoleRepository.save(sysRole);
		}else{//有这个角色，更新
			role.setSysAuthorities(null);
			sysRoleRepository.save(sysRole);
			/**
			 * 更新后需要  <<重新生成 >>auth账户对应的权限
			 */
			List<SysUser> sysUsers = sysUserRepository.findSysUsersBySysRoles(sysRole);
			for(SysUser sysUser : sysUsers){
				authClient.updateSysUserRole(sysUser);
			}
			
		}
		return "success";
	}

	@Override
	public String updateSysUserRole(String username, String[] roleNames) {
		Set<SysRole> roles = new HashSet<SysRole>();
		for(String roleName : roleNames){
			SysRole role = sysRoleRepository.findOne(roleName);
			if(role!=null) roles.add(role);
		}
		//如果指定的角色都没有
		if(roles.size()==0) return "找不到任何的角色名";
		
		SysUser user = sysUserRepository.findOne(username);
		
		if(user==null) return "找不到指定的用户名";
		
		user.setSysRoles(roles);
		
		sysUserRepository.save(user);
		authClient.updateSysUserRole(user);
		
		return "success";
	}

	@Override
	public Page<SysUser> listAllSysUsers(PageParam pageParam) {
		
		Specification<SysUser> specification = new Specification<SysUser>(){

			//不指定查询条件
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
                return cb.and(predicates.toArray(new Predicate[0]));
			}
			
		};
		//分页信息
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getLimit()); //页码：前端从1开始，jpa从0开始，做个转换
		
		return sysUserRepository.findAll(specification, pageable);
	}

	@Override
	public Page<SysRole> listAllSysRoles(PageParam pageParam) {
		Specification<SysRole> specification = new Specification<SysRole>(){

			//不指定查询条件
			@Override
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
                return cb.and(predicates.toArray(new Predicate[0]));
			}
			
		};
		//分页信息
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getLimit()); //页码：前端从1开始，jpa从0开始，做个转换
		
		return sysRoleRepository.findAll(specification, pageable);
	}
	
	
}
