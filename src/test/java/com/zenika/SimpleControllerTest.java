package com.zenika;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class SimpleControllerTest {

    @Autowired private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test public void helloWorld() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Hello World!"));
    }

    @Configuration
    public static class TestConfiguration {

        @Bean public SimpleController simpleController() {
            return new SimpleController();
        }

    }
}
