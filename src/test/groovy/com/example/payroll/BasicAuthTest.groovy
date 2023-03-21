package com.example.payroll

import com.example.payroll.feign.client.BasicAuthClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasicAuthTest extends Specification{

    @Autowired
    BasicAuthClient client;

    def "basic auth"(){
        expect:
        client.auth(200);
    }
}
