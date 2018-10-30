package com.syxt.base;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages="com.syxt.base.domain")
@EnableJpaRepositories(basePackages= "com.syxt.base.repository")
@EnableTransactionManagement
public class HibernateJpaConfig {

}
