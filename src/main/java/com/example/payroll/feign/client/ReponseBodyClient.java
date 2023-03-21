package com.example.payroll.feign.client;

import com.example.payroll.feign.configuration.HeaderConfiguration;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="responseBodyClient", url="https://httpbin.org", configuration = {HeaderConfiguration.class})
public interface ReponseBodyClient {

	@GetMapping(value = "/headers")
	public Response body(@RequestHeader("my-test-header") String headersValue);

}
