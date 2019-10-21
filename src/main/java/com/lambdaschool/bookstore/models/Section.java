package com.lambdaschool.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "section", description = "Section Entity")
@Entity
@Table(name ="section")
public class Section extends Auditable
{
    @ApiModelProperty(name = "sectionid", value = "Primary Section id", required = true, example = "2" )
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    @ApiModelProperty(name = "sectionname", value = "Section Name", example = "Sci-fi")
    private String sectionname;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<Book> books = new ArrayList<>();

    public Section() {
    }

    public Section(String sectionname, List<Book> books) {
        this.sectionname = sectionname;
        this.books = books;
    }

    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionid=" + sectionid +
                ", sectionname='" + sectionname + '\'' +
                ", books=" + books +
                '}';
    }
}
