package com.edgar.bautista.samplecode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SampleCodeApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private String url;

    @BeforeEach
    void initTest() {
        url = "/api/json";
    }

    @Test
    void contextLoads() throws Exception {
        // TODO: move to src/test/resources
        String defaultPayload = "{\n" +
                "    \"test\": \"value\",\n" +
                "    \"test2\": [\n" +
                "        {\n" +
                "            \"nested\": {\n" +
                "                \"nested2\": \"ok\",\n" +
                "                \"intField\": 20,\n" +
                "                \"other\": [\"more\"],\n" +
                "                \"other2\": [\n" +
                "                    {\"otherNested\": [{ \"other\": \"tt\", \"otherNum\": 2 }]}\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"test3\": {\n" +
                "        \"nested\": 30,\n" +
                "        \"nested2\": 10\n" +
                "    }\n" +
                "}";
        String defaultOutput = "test::STRING\n" +
                "test2::ARRAY\n" +
                "test2:nested:nested2::STRING\n" +
                "test2:nested:intField::NUMBER\n" +
                "test2:nested:other::ARRAY\n" +
                "test2:nested:other::STRING\n" +
                "test2:nested:other2::ARRAY\n" +
                "test2:nested:other2:otherNested::ARRAY\n" +
                "test2:nested:other2:otherNested:other::STRING\n" +
                "test2:nested:other2:otherNested:otherNum::NUMBER\n" +
                "test3:nested::NUMBER\n" +
                "test3:nested2::NUMBER";
        this.mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(defaultPayload))
                .andExpect(status().isOk()).andExpect(content().string(containsString(defaultOutput)));
    }

}
