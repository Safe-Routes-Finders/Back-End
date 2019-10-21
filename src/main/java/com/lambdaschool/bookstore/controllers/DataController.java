package com.lambdaschool.bookstore.controllers;


import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Deletes a Book by the Book's ID", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Found", responseContainer = "List", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)})
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookid)
    {
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a Book by Id.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Updated!", response = void.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating Book", response = ErrorDetail.class)
    } )
    @PutMapping(value = "/books/{bookid}")
    @PreAuthorize("hasAuthority('ROLE_DATA')")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long bookid)
    {
        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    ******************** UPDATE BOOK WITH AUTHOR**********************
    @ApiOperation(value = "Adds New Book.", notes = "Assigns a book already in the system (bookid) to an author " +
            "already in the system (authorid) "
         , response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating book", response = ErrorDetail.class)
    } )

    public ResponseEntity<?> addAuthor(@PathVariable long bookid,
                                        @PathVariable long authorid)
    {
        bookService.addAuthor(bookid, authorid);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
