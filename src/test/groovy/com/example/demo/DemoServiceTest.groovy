package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@SuppressWarnings('GroovyAccessibility')
class DemoServiceTest extends Specification {
    @Autowired
    private DemoService demoService

    @Unroll
    def "shiftThreeOrLessToRight converts #input to #output"() {
        expect:
        demoService.shiftThreeOrLessToRight(input) == output
        where:
        input    | output
        43256791 | 45326791
    }


    @Unroll
    def "multiplyEightOrNine converts #input to #output"() {
        expect:
        demoService.multiplyEightOrNine(input) == output
        where:
        input    | output
        45326791 | 453267181
    }

    @Unroll
    def "removeSevens converts #input to #output"() {
        expect:
        demoService.removeSevens(input) == output
        where:
        input     | output
        453267181 | 45326181
    }

    @Unroll
    def "divideByEvenDigitCount converts #input to #output"() {
        expect:
        demoService.divideByEvenDigitCount(input) == output
        where:
        input    | output
        45326181 | 11331545
    }

    @Unroll
    def "processNumber converts #input to #output"(){
        expect:
        demoService.processNumber(input) == output
        where:
        input    | output
        43256791 | 11331545
    }


}
