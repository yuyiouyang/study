package com.syxt.base.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syxt.base.domain.SysRole;

@Repository
public interface SysRoleRepository extends CrudRepository<SysRole, String> {

	Page<SysRole> findAll(Specification<SysRole> specification, Pageable pageable);
}
