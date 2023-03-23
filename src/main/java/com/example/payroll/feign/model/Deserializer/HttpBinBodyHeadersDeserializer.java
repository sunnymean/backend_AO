package com.example.payroll.feign.model.Deserializer;

import com.example.payroll.feign.model.HttpbinBodyHeader;
import com.example.payroll.feign.model.HttpbinResponse;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;

public class HttpBinBodyHeadersDeserializer extends StdDeserializer<HttpbinBodyHeader> {

//	private final ObjectMapper objectMapper;

	protected HttpBinBodyHeadersDeserializer() {
		super(HttpbinBodyHeader.class);
		/*this.objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(HttpbinBodyHeader.class, new HttpBinBodyHeadersDeserializer());
		this.objectMapper.registerModule(simpleModule);*/
	}

	protected HttpBinBodyHeadersDeserializer(Class vc) {
		super(vc);
	}

	@Override
	public HttpbinBodyHeader deserialize(JsonParser p, DeserializationContext ctxt)
		throws IOException, JacksonException {

		ObjectCodec objectCodec = p.getCodec();
		JsonNode jsonNode = objectCodec.readTree(p);

		final String Accept = jsonNode.get("Accept").asText();
		final String Content_Type = jsonNode.get("Content-Type").asText();;
		final String User_Agent = jsonNode.get("User-Agent").asText();;
		final String X_Amzn_Trace_Id = jsonNode.get("X-Amzn-Trace-Id").asText();;

		/*final String origin = jsonNode.get("origin").asText();
		final String url = jsonNode.get("url").asText();*/

		return new HttpbinBodyHeader(Accept, Content_Type, User_Agent, X_Amzn_Trace_Id);
	}
}
