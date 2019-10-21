package com.lambdaschool.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "author", description = "Author Entity")
@Entity
@Table(name = "author")
public class Author  extends Auditable
{

    @ApiModelProperty(name = "authorid", value = "Author Id", required = true, example = "2")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name = "lastname", value = "Author Last Name", example = "Whithers")
    private String lastname;

    @ApiModelProperty(name = "firstname", value = "Author First Name", example = "Bill")
    private String firstname;

    @ManyToMany
    @JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "authorid")}, inverseJoinColumns =
            {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("author")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String lastname, String firstname, List<Book> books) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.books = books;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorid=" + authorid +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", books=" + books +
                '}';
    }
}
