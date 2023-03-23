package com.example.payroll

import com.example.payroll.feign.client.SimpleClient
import com.example.payroll.feign.model.HttpbinBodyHeader
import com.example.payroll.feign.model.HttpbinRequestInfo
import com.example.payroll.feign.model.HttpbinResponse
import com.example.payroll.feign.model.HttpbinResponseInfo
import com.example.payroll.repository.EmployeeRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes =[PayrollApplication.class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SimpleClientTest extends Specification {

    @Autowired
    SimpleClient simpleClient;

    @Autowired
    EmployeeRepository repository;

    def "status check"(){
        given:
        HttpbinRequestInfo requestInfo = HttpbinRequestInfo.builder().second(10).build();

        expect:
        //simpleClient.status(201)
        //simpleClient.testErrorDecoderByDelay(3);
        HttpbinResponseInfo responseInfo = simpleClient.testErrorDecoderByDelayPost(requestInfo, 1);
        responseInfo.getContent_length() == "51";
        responseInfo.getHeaders().get("Content-Type") == "application/json";

        when:
        HttpbinResponse response = simpleClient.testResponseMapping(requestInfo, 1);
//        HttpbinBodyHeader headers = response.getHeaders();
        HttpbinBodyHeader headers = response.getHeaders().getValue();

        then:
        headers != null
        headers.getContentType() != null
        headers.getContentType() == "application/json";

        log.debug("response url is {}, context type is {}", response.getUrl(), headers.getContentType());
    }
}
