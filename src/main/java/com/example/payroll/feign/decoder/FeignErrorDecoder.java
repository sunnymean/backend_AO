package com.example.payroll.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


/**
 * ErrorDecoder is not called when an IOException (SocketTimeoutException) is thrown. See
 * SynchronousMethodHandler#executeAndDecode(...). In general decoders are only called when a response is returend by
 * the api-call. The ErrorCoder is only called when the http error code is not 2xx and 4xx.
 */
@Slf4j
public final class FeignErrorDecoder implements ErrorDecoder {

	/*@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == HttpStatus.NOT_FOUND.value()) {
			return new ReservationExpireException("tcc reservation expired", response);
		}
		if (response.status() == HttpStatus.CONFLICT.value()) {
			String conflictionDetails;
			try {
				conflictionDetails = IOUtils.toString(response.body().asInputStream(), Charsets.UTF_8);
			} catch (IOException e) {
				LOGGER.error("read conflict response body exception. {}", e.toString());
				conflictionDetails = "{}";
			}
			return new PartialConfirmException(conflictionDetails);
		}
		return FeignException.errorStatus(methodKey, response);
	}*/
	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		final HttpStatus httpStatus = HttpStatus.resolve(response.status());

		log.debug("=====methodkey : {}, response : {}", methodKey, response.reason());
		// Handle Custom Error Status Code
		// The rest is delegated to the default error decoder
		if (httpStatus == HttpStatus.NOT_FOUND) {
			//if (httpStatus == HttpStatus.REQUEST_TIMEOUT) {
			/*log.warn("Handle Custom Error Status. httpStatus : {}", httpStatus);
			throw new CustomException();*/
			log.warn("[Slf4j] Http Status = {}", httpStatus);
			throw new RuntimeException(String.format("[RuntimeException] Http Status is %s", httpStatus));
		}

		return errorDecoder.decode(methodKey, response);
	}
}

/**
 * @Override public Object getPayload() throws ClientException { return this.response.body(); }
 * @Override public void close() throws IOException { if (response != null && response.body() != null) {
 * response.body().close(); } }
 * @Override public void close() throws IOException { if (this.response != null && this.response.body() != null) {
 * this.response.body().close(); } }
 */