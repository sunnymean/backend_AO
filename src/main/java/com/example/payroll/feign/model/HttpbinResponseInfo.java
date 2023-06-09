package com.example.payroll.feign.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
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


	/*{
  "args": {},
  "data": "",
  "files": {},
  "form": {},
  "headers": {
    "Accept": "application/json",
    "Accept-Encoding": "gzip, deflate, br",
    "Accept-Language": "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7",
    "Cookie": "my-cookie-key=sparta",
    "Host": "httpbin.org",
    "Referer": "https://httpbin.org/",
    "Sec-Ch-Ua": "\"Google Chrome\";v=\"111\", \"Not(A:Brand\";v=\"8\", \"Chromium\";v=\"111\"",
    "Sec-Ch-Ua-Mobile": "?0",
    "Sec-Ch-Ua-Platform": "\"macOS\"",
    "Sec-Fetch-Dest": "empty",
    "Sec-Fetch-Mode": "cors",
    "Sec-Fetch-Site": "same-origin",
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36",
    "X-Amzn-Trace-Id": "Root=1-641a6700-033c1ec74231070f07dae77c"
  },
  "origin": "210.105.10.123",
  "url": "https://httpbin.org/delay/1"
}*/
	private Map<String, Object> args;
	private String datas;
//	private Map<String, Object> files;

	//private BinBodyHeader bodyHeader;
//	private String origin;
//	private String url;

	private Map<String, Object> headers;
	private String accept;
	private String content_length;



	@SuppressWarnings("unchecked")
	@JsonProperty("headers") //http header 아니고 body 에 있는 header ㄴ 라는 노드
	private void unpackNested(Map<String,Object> headers) {
		this.headers = headers;
		this.accept = (String)headers.get("Accept");
		//Map<String,String> apart = (Map<String,String>)headers.get("apart");
		this.content_length = (String)headers.get("Content-Length");
	}
	@SuppressWarnings("unchecked")
	@JsonProperty("args") //http header 아니고 body 에 있는 header ㄴ 라는 노드
	private void unpackNestedData(Map<String,Object> args) {
		this.args = args;
	}


}
