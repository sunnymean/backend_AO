package com.example.payroll.feign.client;

import com.example.payroll.feign.configuration.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="headerClient", url="https://httpbin.org", configuration = {HeaderConfiguration.class})
public interface HeaderClient {

	@GetMapping(value = "/status/{status}")
	void status(@PathVariable("status") int status);

	@GetMapping(value = "/status/{status}", headers = "key2=value2")
	void status2(@PathVariable("status") int status);

	@GetMapping(value = "/status/{status}")
	void status3(@RequestHeader("key3") String headers, @PathVariable("status") int status);
}
