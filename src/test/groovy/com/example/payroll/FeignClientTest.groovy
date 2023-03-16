package com.example.payroll

import com.example.payroll.feign.client.SimpleClient
import com.example.payroll.model.Employee
import com.example.payroll.repository.EmployeeRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes =[PayrollApplication.class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class FeignClientTest extends Specification {

    @Autowired
    SimpleClient simpleClient;

    @Autowired
    EmployeeRepository repository;

    def "status check"(){
        expect:
        simpleClient.status(201)

        when:
        List<Employee> list = repository.findAll()

        then:
        list.size() >= 9
        log.info("list size is " + list.size());
    }
}
