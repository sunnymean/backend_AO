package com.example.payroll.feign.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class BasicAuthConfiguration {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("sunnymean", "q1w2e3r4");
	}
}
