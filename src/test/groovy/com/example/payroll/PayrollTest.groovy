package com.example.payroll


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes =[TestConfiguration])
@SpringBootTest
class PayrollTest extends Specification {

    def "first test"() {
        expect:
        1 == 1
    }

    def "check addition of 2 numbers"() {

        given:
        int input1 = 10
        int input2 = 25

        expect:
        input1.getClass().toString() == "class java.lang.Integer"
        input2.getClass().toString() == "class java.lang.Integer"
        input1 <= Integer.MAX_VALUE
        input1 >= Integer.MIN_VALUE

        when:
        int result = input1 + input2

        then:
        result == 35
    }
}
