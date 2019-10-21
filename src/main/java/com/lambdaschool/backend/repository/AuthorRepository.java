package com.lambdaschool.backend.repository;

import com.lambdaschool.backend.models.Author;
import com.lambdaschool.backend.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends PagingAndSortingRepository <Author, Long> {

    @Query(value = "SELECT COUNT(*) as count FROM author WHERE bookid = :bookid AND authorid = :authorid",
            nativeQuery = true)
    JustTheCount checkAuthorBookCombo(long bookid,
                                     long authorid);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wrote(bookid, authorid) VALUES (:bookid, :authorid)",
            nativeQuery = true)
    void insertBookAuthors(long bookid,
                         long authorid);

}
