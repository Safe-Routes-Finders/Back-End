package com.lambdaschool.backend.controllers;

import com.lambdaschool.backend.models.*;
import com.lambdaschool.backend.services.IncidentService;
import com.lambdaschool.backend.services.RoleService;
import com.lambdaschool.backend.services.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class IncidentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    private List<Incident> incidentList;

    @Before
    public void setUp() throws Exception
    {

//        Incident Seed Data //
        incidentList = new ArrayList<>();

        Incident i1 = new Incident("(Test 30.3427, -187.3258)", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13, 14,
                                   15 , 16,
                                   17, 18, 19, 20, 21, 22, 23, 33.3427, 66.3427);
        incidentService.saveIncident(i1);


        Incident i2 = new Incident("(Test 54.3427, -1837.3258)", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0 , 0,
                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i2);

        Incident i3 = new Incident("(Test 2.3427, -2.3258)", 1, 0, 1, 1, 1, 0, 0, 0, 1, 1 , 0, 0, 0, 0, 0 , 0,
                                   0, 0, 0, 0, 0, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i3);

        Incident i4 = new Incident("(Test 4.3427, -4.3258)", 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 , 20, 0, 1, 0, 0 , 0,
                                   0, 0, 0, 0, 1, 0, 0, 33.3427, 66.3427);
        incidentService.saveIncident(i4);

        System.out.println("*** INCIDENT SEED DATA ****");
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println("*** INCIDENT SEED DATA ***");

    }

    @After
    public void tearDown() throws Exception
    {
    }


    @Test
    void listAllIncidents() throws Exception{
        String apiUrl = "/incidents/incidents";

        Mockito.when(incidentService.findAll(any(Pageable.class))).thenReturn(incidentList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(incidentList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    void listTopIncidents() throws Exception {
    }
}