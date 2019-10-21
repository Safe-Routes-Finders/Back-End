package com.lambdaschool.backend.services;

import com.lambdaschool.backend.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findAll(Pageable pageable);

    void delete(long bookid);

    Book update(Book book, long bookid);

    Book save(Book book);

    void addAuthor(long bookid,
                     long authorid);
}
