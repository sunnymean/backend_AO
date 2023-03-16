package com.example.payroll.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleClient", url="https://httpbin.org"/*, configuration = {HeaderConfiguration.class}*/)
public interface SimpleClient {

	/*@PostMapping(value = "/anything")
	void anything(@RequestBody ExampleRequest request);*/

    @GetMapping("/status/{status}")
    void status(@PathVariable("status") int status);

}
