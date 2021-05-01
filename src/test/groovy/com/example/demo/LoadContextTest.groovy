package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired(required = false)
    private DemoController demoController
    @Autowired(required = false)
    private DemoService demoService

    def "when context is loaded then all expected beans are created"() {
        expect: "the demo beans are created"
        demoController
        demoService
    }
}