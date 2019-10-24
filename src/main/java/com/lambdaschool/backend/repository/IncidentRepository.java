package com.lambdaschool.backend.repository;

import com.lambdaschool.backend.models.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IncidentRepository extends PagingAndSortingRepository<Incident, Long> {

    @Query(value = "SELECT * FROM INCIDENTS ORDER BY ALL_YEARS DESC LIMIT 25", nativeQuery = true)
    public List<Incident> returnTopIncidents();
}
