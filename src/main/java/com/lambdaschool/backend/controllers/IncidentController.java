package com.lambdaschool.backend.controllers;

import com.lambdaschool.backend.logging.Loggable;
import com.lambdaschool.backend.models.Incident;
import com.lambdaschool.backend.services.IncidentService;
import com.lambdaschool.backend.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/incidents")
public class IncidentController {

    private static final Logger logger = LoggerFactory.getLogger(IncidentController.class);


    @Autowired
    private IncidentService incidentService;

    @ApiOperation(value = "Returns all Incidents, Pageable.", notes = "Returns 10 incidents per page.",
            response = Incident.class,
            responseContainer = "List")
    @ApiImplicitParams({@ApiImplicitParam(name = "page",
            dataType = "integer",
            paramType = "query",
            value = "Results page you want to retrieve (0..N)"), @ApiImplicitParam(name = "size",
            dataType = "integer",
            paramType = "query",
            value = "Number of records per page."), @ApiImplicitParam(name = "sort",
            allowMultiple = true,
            dataType = "string",
            paramType = "query",
            value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returning List", response = Incident.class)})
    @GetMapping(value = "/incidents", produces = {"application/json"})
    public ResponseEntity<?> listAllIncidents(HttpServletRequest request,
                                              @PageableDefault(page = 0, size = 10) Pageable pageable)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + "accessed");

        List<Incident> allIncident = incidentService.findAll(pageable);

        return new ResponseEntity<>(allIncident, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a list of 25 areas with the most incidents.",
            response = Incident.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returning Top 25 Incidents", response = Incident.class)})
    @GetMapping(value = "/incidents/toplocations", produces = {"application/json"})
    public ResponseEntity<?> listTopIncidents(HttpServletRequest request
                                             )
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + "accessed");

        List<Incident> topIncidents = incidentService.topIncident();

        return new ResponseEntity<>(topIncidents, HttpStatus.OK);
    }

}
