package com.syxt.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syxt.base.domain.SysUser;

@FeignClient(name = "syxt-auth-service")
public interface AuthServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/uaa/users/createUser", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(SysUser user);
	
	@RequestMapping(method = RequestMethod.POST, value = "/uaa/users/updateSysUserRole", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void updateSysUserRole(SysUser user);
}
