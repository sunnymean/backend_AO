package com.example.payroll

import com.example.payroll.feign.client.ReponseBodyClient
import feign.Response
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeignReponseBodyTest extends Specification{
    @Autowired
    ReponseBodyClient client;

    def checkBody() {
        when:
        Response response = client.body("wyees barsoom");
        log.info(response.toString());
        log.info("status : " + response.status());
        log.info("reason : " + response.reason());
        log.info("request : " + response.request().toString());
        log.info("headers : " + response.headers().toString());
        log.info("body : " + response.body().toString());

        then:
        response.status() == 200


    }
}
