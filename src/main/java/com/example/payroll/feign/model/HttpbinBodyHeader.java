package com.example.payroll.feign.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class HttpbinBodyHeader {

	@JsonProperty("Acctep")
	private String accept;
	@JsonProperty("Content-Type")
	private String contentType;

	@JsonProperty("User-Agent")
	private String userAgnet;
	@JsonProperty("X-Amzn-Trace-Id")
	private String xAmznTraceId;
}
