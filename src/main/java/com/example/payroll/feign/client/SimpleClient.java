package com.example.payroll.feign.client;

import com.example.payroll.feign.configuration.FeignConfiguration;
import com.example.payroll.feign.model.HttpbinRequestInfo;
import com.example.payroll.feign.model.HttpbinResponse;
import com.example.payroll.feign.model.HttpbinResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "simple-client", url = "${feign.demo.http-bin}", configuration = {FeignConfiguration.class})
public interface SimpleClient {

	/*@PostMapping(value = "/anything")
	void anything(@RequestBody ExampleRequest request);*/

	@GetMapping("/status/{status}")
	void status(@PathVariable("status") int status);

	@GetMapping(value = "/delay/{second}", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	void testErrorDecoderByDelay(@PathVariable(name = "second") int delaySecond);

	@PostMapping(value = "/delay/{second}", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	HttpbinResponseInfo testErrorDecoderByDelayPost(@RequestBody HttpbinRequestInfo requestInfo,
		@PathVariable(name = "second") int delaySecond);


	@PostMapping(value = "/delay/{second}", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	HttpbinResponse testResponseMapping(@RequestBody HttpbinRequestInfo requestInfo,
		@PathVariable(name = "second") int delaySoconds);
}
