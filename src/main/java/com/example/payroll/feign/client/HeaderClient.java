package com.example.payroll.feign.client;

import com.example.payroll.feign.configuration.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/*request header 입력 / 확인 방법*/
@FeignClient(name="headerClient", url="https://httpbin.org", configuration = {HeaderConfiguration.class})
public interface HeaderClient {

	@GetMapping(value = "/status/{status}")
	void status(@PathVariable("status") int status);

	@GetMapping(value = "/status/{status}", headers = "my-test-header2=wyeems basum")
	void status2(@PathVariable("status") int status);

	@GetMapping(value = "/status/{status}")
	void status3(@RequestHeader("my-test-header3") String headers, @PathVariable("status") int status);

	@GetMapping(value = "/headers")
	void echoHeader(@RequestHeader("my-test-header4") String headers);
}
