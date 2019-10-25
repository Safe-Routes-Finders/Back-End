package com.lambdaschool.backend.controllers;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IncidentControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                 .apply(SecurityMockMvcConfigurers.springSecurity())
                                 .build();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @WithUserDetails("(Test 30.3427, -187.3258)")
    @Test
    public void A_whenMeasuredResponseTime()
    {
        given().when()
               .get("/incidents/incidents")
               .then()
               .time(lessThan(5000L));
    }

    @WithUserDetails("(Test 30.3427, -187.3258)")
    @Test
    public void B_getAllUsers() throws Exception
    {
        this.mockMvc.perform(get("/incidents/incidents"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("(Test 30.3427, -187.3258)")));
    }
}
