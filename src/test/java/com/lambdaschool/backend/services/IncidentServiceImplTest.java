package com.lambdaschool.backend.services;

import com.lambdaschool.backend.SaferBackEndApplication;
import com.lambdaschool.backend.models.Incident;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SaferBackEndApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IncidentServiceImplTest {

    @Autowired IncidentService incidentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll()
    {
        assertEquals(4, incidentService.findAll(Pageable.unpaged()).size());
    }

    @Test
    public void topIncident() {
        assertEquals(4, incidentService.topIncident().size());
    }

    @Test
    public void saveIncident()
    {
        ArrayList<Incident> datas = new ArrayList<>();
        Incident i7 = new Incident("(9.3427, -4.3258)", 9, 9, 9, 9, 1, 0, 0, 1, 1, 1 , 20, 0, 1, 0, 0 , 0,
                                   9, 9, 9, 9, 9, 9, 9, 33.3427, 66.3427);

        Incident savei7 = incidentService.saveIncident(i7);

        System.out.println("*** DATA ***");
        System.out.println(savei7);
        System.out.println("*** DATA ***");

        assertEquals("(9.3427, -4.3258)", savei7.getLocation());
    }
}