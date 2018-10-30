package com.syxt.base.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syxt.base.domain.SysRole;
import com.syxt.base.domain.SysUser;

@Repository
public interface SysUserRepository extends CrudRepository<SysUser, String> {
	
	List<SysUser> findSysUsersBySysRoles(SysRole sysRole);
	//分页查询用户列表
	Page<SysUser> findAll(Specification<SysUser> spec, Pageable pageable);
}
