package com.lambdaschool.bookstore.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "book", description = "Book Entity")
@Entity
@Table(name = "book")
public class Book extends Auditable
{

    @ApiModelProperty(name = "bookid", value = "Book Id", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;


    @ApiModelProperty(name = "booktitle", value = "Book Title", example = "The Cat In The Hat")
    @Column(nullable = false)
    private String booktitle;

    @ApiModelProperty(name = "ISBN", value = "Book ISBN", example = "978-3-16-148410-0")
    @Column(nullable = false, unique = true)
    private String ISBN;

    @ApiModelProperty(name = "copy", value = "Year book was published", example = "1970")
    private int copy;

    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sectionid", nullable = false)
    @JsonIgnoreProperties("book")
    private Section section;

    public Book() {
    }


    public Book(String booktitle, String ISBN, int copy, List<Author> authors, Section section) {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
        this.authors = authors;
        this.section = section;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", booktitle='" + booktitle + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", copy=" + copy +
                ", authors=" + authors +
                ", section=" + section +
                '}';
    }
}
