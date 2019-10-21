package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceFoundException;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "BookService")
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authrepos;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long bookid) throws EntityNotFoundException
    {
        if (bookrepos.findById(bookid).isPresent())
        {
            bookrepos.deleteBookFromAuthbooks(bookid);
            bookrepos.deleteById(bookid);
        } else
        {
            throw new EntityNotFoundException(Long.toString(bookid));
        }
    }

    @Transactional
    @Override
    public Book update(Book book, long bookid)
    {
        Book currentBook = bookrepos.findById(bookid)
                                                .orElseThrow(() -> new EntityNotFoundException(Long.toString(bookid)));

        if (book.getBooktitle() != null){
            currentBook.setBooktitle(book.getBooktitle());
        }

        if (book.getISBN() != null){
            currentBook.setISBN(book.getISBN());
        }

        if(book.getCopy() != 0){
            currentBook.setCopy(book.getCopy());
        }

        if(book.getAuthors() != null){
            currentBook.setAuthors((book.getAuthors()));
        }

        if(book.getSection() != null){
            currentBook.setSection(book.getSection());
        }

        return bookrepos.save(currentBook);
    }

    @Transactional
    @Override
    public void addAuthor(long bookid, long authorid)
    {
        bookrepos.findById(bookid).orElseThrow(() -> new ResourceNotFoundException("Book id" + bookid + "Not found!"));

        authrepos.findById(authorid).orElseThrow(() -> new ResourceNotFoundException("Author id " + authorid + " not " +
                                                                                             "found!"));

        if (authrepos.checkAuthorBookCombo(bookid, authorid).getCount() <=0)
        {
            authrepos.insertBookAuthors(bookid, authorid);
        } else
        {
            throw new ResourceFoundException("Author & book combination already exists");
        }
    }

    @Transactional
    @Override
    public Book save(Book book)
    {

        Book newBook = new Book();

        newBook.setBooktitle(book.getBooktitle().toLowerCase());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());
        newBook.setAuthors(book.getAuthors());
        newBook.setSection(book.getSection());

        return bookrepos.save(newBook);
    }
}
