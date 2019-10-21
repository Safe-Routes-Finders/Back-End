package com.lambdaschool.backend.services;

import com.lambdaschool.backend.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    List<Author> findAll(Pageable pageable);
}
