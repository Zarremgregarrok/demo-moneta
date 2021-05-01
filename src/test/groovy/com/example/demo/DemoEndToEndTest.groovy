package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
class DemoEndToEndTest extends Specification {
    @Autowired
    MockMvc mvc

    @Unroll
    def "when get on /number?number=#input is performed then the response has status 200 and content is '#output'"() {
        when:
        def results = mvc.perform(
                get('/number')
                        .param("number", "" + input)
        )
        then:
        results
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == output.toString()
        where:
        input    | output
        43256791 | 11331545
    }
}
