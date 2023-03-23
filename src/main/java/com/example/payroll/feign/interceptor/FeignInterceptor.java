package com.example.payroll.feign.interceptor;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.example.payroll.feign.model.HttpbinRequestInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class FeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {

		if (template.body() == null) {
			return;
		}

		String oldMessage = StringUtils.toEncodedString(template.body(), UTF_8);
		log.info("[FeignInterceptor] Old Message. {}", oldMessage);

		ObjectMapper objectMapper = new ObjectMapper();

		HttpbinRequestInfo oldInfo = null;
		String newMessage = null;

		try {
			oldInfo = objectMapper.readValue(oldMessage, HttpbinRequestInfo.class);
			HttpbinRequestInfo newInfo = HttpbinRequestInfo.builder()
														   .explanation("[httpbin test api] " + oldInfo.getSecond())
														   .second(oldInfo.getSecond() + 20).build();
			newMessage = objectMapper.writeValueAsString(newInfo);
//			newMessage = "OK::" + oldMessage;
		} catch (JsonProcessingException e) {
			log.warn("Error occurred while parsing objectMapper. ", e);
			newMessage = oldMessage;
		}
		log.info("[DemoFeignInterceptor] New Message. {}", newMessage);
		template.body(newMessage);
	}
}
