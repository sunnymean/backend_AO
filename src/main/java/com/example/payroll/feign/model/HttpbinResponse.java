package com.example.payroll.feign.model;

import com.example.payroll.feign.model.Deserializer.HttpBinBodyHeadersDeserializer;
import com.example.payroll.feign.model.Deserializer.WrapperDeserializer;
import com.example.payroll.feign.model.Deserializer.Wrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class HttpbinResponse {
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
  "origin": "210.105.10.123",®
  "url": "https://httpbin.org/delay/1"
}*/
	private String origin;

	private String url;

//	@JsonDeserialize(using = HttpBinBodyHeadersDeserializer.class)
	@JsonDeserialize(using = WrapperDeserializer.class) //클래스타입 마다 등록된 디시리얼라이져 사용
	private Wrapper<HttpbinBodyHeader> headers;

}
