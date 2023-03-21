package com.example.payroll.feign.client;

import com.example.payroll.feign.configuration.BasicAuthConfiguration;
import com.example.payroll.feign.configuration.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="basicAuthClient", url="https://httpbin.org", configuration = {BasicAuthConfiguration.class})
public interface BasicAuthClient {

	@GetMapping("/status/{status}")
	void auth(@PathVariable("status") int status);
}
