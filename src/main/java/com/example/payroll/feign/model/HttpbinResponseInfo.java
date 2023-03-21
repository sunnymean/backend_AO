package com.example.payroll.feign.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class HttpbinResponseInfo {

	private List<String> args;
	private String data;
	private List<String> files;

	private BinBodyHeader bodyHeader;

	private String origin;
	private String url;


}
