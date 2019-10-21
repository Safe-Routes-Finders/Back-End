package com.lambdaschool.backend.repository;

import com.lambdaschool.backend.models.Section;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SectionRepository extends PagingAndSortingRepository <Section, Long> {
}
