package com.example.payroll

import com.example.payroll.feign.client.HeaderClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeignHeaderClientTest extends Specification {
    @Autowired
    HeaderClient headerClient

    def "status"() {
        expect:
        headerClient.status(200)
    }

    def "status2"() {
        expect:
        headerClient.status2(200)
    }

    def "status3"() {
        expect:
        headerClient.status3("wyees barsoom", 200)
    }

    def "echo header"() {
        expect:
        headerClient.echoHeader("wyees barsoom");
    }
}
