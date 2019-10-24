package com.lambdaschool.backend.controllers;

import com.lambdaschool.backend.logging.Loggable;
import com.lambdaschool.backend.models.ErrorDetail;
import com.lambdaschool.backend.models.User;
import com.lambdaschool.backend.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // http://localhost:2019/users/users/?page=1&size=1
    // http://localhost:2019/users/users/?sort=username,desc&sort=<field>,asc
    @ApiOperation(value = "Returns all Users, Pageable : 5 Users per page.",
                  response = User.class,
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success! Returning All Users, 5 will be displayed per " +
            "page", response = User.class)})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request,
                                          @PageableDefault(page = 0,
                                                           size = 5)
                                                  Pageable pageable)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll(pageable);
        return new ResponseEntity<>(myUsers,
                                    HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all Users, Non pageable.",
            response = User.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success! Returning All Users", response = User.class)})
    // http://localhost:2019/users/users/all
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users/all",
                produces = {"application/json"})
    public ResponseEntity<?> reallyListAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll(Pageable.unpaged());
        return new ResponseEntity<>(myUsers,
                                    HttpStatus.OK);
    }


    // http://localhost:2019/users/user/7
    @ApiOperation(value = "Finds a user by Id.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Found!", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserById(HttpServletRequest request,
                                         @PathVariable
                                                 Long userId)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/users/user/name/cinnamon

    @ApiOperation(value = "Finds a user by Name.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Found!", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserByName(HttpServletRequest request,
                                           @PathVariable
                                                   String userName)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/users/user/name/like/da?sort=username
    @ApiOperation(value = "Returns all Users with names containing a given string.",
                  response = User.class,
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found Users", response = void.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/name/like/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> getUserLikeName(HttpServletRequest request,
                                             @PathVariable
                                                     String userName,
                                             @PageableDefault(page = 0,
                                                              size = 5)
                                                     Pageable pageable)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> u = userService.findByNameContaining(userName,
                                                        pageable);
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/users/getusername
    @ApiOperation(value = "Returns the current user's name.",
            response = User.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Your Name is", response = void.class)
    } )

    @GetMapping(value = "/getusername",
                produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request,
                                                Authentication authentication)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(),
                                    HttpStatus.OK);
    }

    // http://localhost:2019/users/getuserinfo

    @ApiOperation(value = "Returns the current users info.",
            response = User.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning your info", response = void.class)
    } )

    @GetMapping(value = "/getuserinfo",
                produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(HttpServletRequest request,
                                                Authentication authentication)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                                    HttpStatus.OK);
    }

    // http://localhost:2019/users/user
    //        {
//                "username": "Mojo",
//                "primaryemail": "mojo@lambdaschool.local",
//                "password" : "Coffee123",
//                "useremails": [
//                {
//                    "useremail": "mojo@mymail.local"
//                },
//                {
//                    "useremail": "mojo@mymail.local"
//                },
//                {
//                    "useremail": "mojo@email.local"
//                }
//            ]
//            }

    @ApiOperation(value = "Adds New User.", notes = "Creates New User."
            , response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating User", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request,
                                        @Valid
                                        @RequestBody
                                                User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                                                    .path("/{userid}")
                                                    .buildAndExpand(newuser.getUserid())
                                                    .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                                    responseHeaders,
                                    HttpStatus.CREATED);
    }


    // http://localhost:2019/users/user/7
//        {
//            "userid": 7,
//                "username": "cinnamon",
//                "primaryemail": "cinnamon@lambdaschool.home",
//                "useremails": [
//            {
//                    "useremail": "cinnamon@mymail.local"
//            },
//            {
//                    "useremail": "hops@mymail.local"
//            },
//            {
//                    "useremail": "bunny@email.local"
//            }
//        ]
//        }

    @ApiOperation(value = "Updates a User by Id.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Updated!", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating User", response = ErrorDetail.class)
    } )

    @PutMapping(value = "/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser,
                           id,
                           request.isUserInRole("ADMIN"));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:2019/users/user/14

    @ApiOperation(value = "Deletes a User by Id.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Deleted!", response = void.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Deleting User", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/users/user/15/role/2

    @ApiOperation(value = "Deletes user access permission based on User ID and Role ID.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Deleted Access Permissions!", response = void.class),
            @ApiResponse(code = 404, message = "User not Found / Role not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error changing User access permission", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> deleteUserRoleByIds(HttpServletRequest request,
                                                 @PathVariable
                                                         long userid,
                                                 @PathVariable
                                                         long roleid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.deleteUserRole(userid,
                                   roleid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:2019/users/user/15/role/2

    @ApiOperation(value = "Adds user access permission based on User ID and Role ID.", notes = "Only Admin's are " +
            "can grant other users admin access." , response =
            void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created User Access Permissions!", response = void.class),
            @ApiResponse(code = 404, message = "User not Found / Role not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Creating User access permission", response = ErrorDetail.class)
    } )

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/{userid}/role/{roleid}")
    public ResponseEntity<?> postUserRoleByIds(HttpServletRequest request,
                                               @PathVariable
                                                       long userid,
                                               @PathVariable
                                                       long roleid)
    {
        logger.trace(request.getMethod()
                            .toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.addUserRole(userid,
                                roleid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}