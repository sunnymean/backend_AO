package com.example.payroll.feign.configuration;

import com.example.payroll.feign.decoder.FeignErrorDecoder;
import com.example.payroll.feign.interceptor.FeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
	/*@Bean
	public Logger feignLogger(ApplicationContext applicationContext) {
		return new FeignCustomLogger(applicationContext);
	}*/

	@Bean
	public FeignErrorDecoder sampleErrorDecoder() {

		return new FeignErrorDecoder();
	}

	@Bean
	public FeignInterceptor sampleMultiLanguageRequestInterceptor() {
		return FeignInterceptor.of();
	}

}
