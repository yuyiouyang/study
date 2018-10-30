package com.syxt.base.client;

import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name = "syxt-statistics-service")
public interface StatisticsServiceClient {

	/*@RequestMapping(method = RequestMethod.PUT, value = "/statistics/{accountName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void updateStatistics(@PathVariable("accountName") String accountName, Account account);*/

}
