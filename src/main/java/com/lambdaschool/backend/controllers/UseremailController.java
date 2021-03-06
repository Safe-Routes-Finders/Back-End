package com.lambdaschool.backend.controllers;

import com.lambdaschool.backend.logging.Loggable;
import com.lambdaschool.backend.models.ErrorDetail;
import com.lambdaschool.backend.models.Useremail;
import com.lambdaschool.backend.services.UseremailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/useremails")
public class UseremailController
{
    private static final Logger logger = LoggerFactory.getLogger(UseremailController.class);

    @Autowired
    UseremailService useremailService;

    // http://localhost:2019/useremails/useremails
    @ApiOperation(value = "lists all User Email addresses", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning Email addresses", response = void.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremails",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUseremails(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Useremail> allUserEmails = useremailService.findAll();
        return new ResponseEntity<>(allUserEmails,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/8

    @ApiOperation(value = "Returns a User's email by UserId", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning Email addresses", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/useremail/{useremailId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserEmailById(HttpServletRequest request,
                                              @PathVariable
                                                      Long useremailId)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        Useremail ue = useremailService.findUseremailById(useremailId);
        return new ResponseEntity<>(ue,
                                    HttpStatus.OK);
    }


    // http://localhost:2019/useremails/username/cinnamon

    @ApiOperation(value = "Returns a User's email by UserName", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning Email addresses", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)
    } )

    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findUseremailByUserName(HttpServletRequest request,
                                                     @PathVariable
                                                             String userName)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Useremail> theUseremails = useremailService.findByUserName(userName,
                                                                        request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(theUseremails,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/8

    @ApiOperation(value = "Deletes User's email based on UserId", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Email addresses", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)
    } )

    @DeleteMapping("/useremail/{useremailid}")
    public ResponseEntity<?> deleteUserEmailById(HttpServletRequest request,
                                                 @PathVariable
                                                         long useremailid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        useremailService.delete(useremailid,
                                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/useremails/useremail/9/email/favbun@hops.local

    @ApiOperation(value = "Updates User's email with userID and email address", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated Email addresses", response = ErrorDetail.class),
            @ApiResponse(code = 404, message = "User not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating email address", response = ErrorDetail.class)
    } )

    @PutMapping("/useremail/{useremailid}/email/{emailaddress}")
    public ResponseEntity<?> updateUserEmail(HttpServletRequest request,
                                             @PathVariable
                                                     long useremailid,
                                             @PathVariable
                                                     String emailaddress)
    {
        useremailService.update(useremailid,
                                emailaddress,
                                request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // note emails are added through the user process
}
