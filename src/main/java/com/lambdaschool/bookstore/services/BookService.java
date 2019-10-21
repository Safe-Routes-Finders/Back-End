package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface BookService {

    List<Book> findAll(Pageable pageable);

    void delete(long bookid);

    Book update(Book book, long bookid);

    Book save(Book book);

    void addAuthor(long bookid,
                     long authorid);
}
