package com.lambdaschool.backend.services;

import com.lambdaschool.backend.models.Incident;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncidentService
{
    List<Incident> findAll(Pageable pageable);

    List<Incident> topIncident();

    Incident saveIncident(Incident incident);

//    Incident findIncidentById(long id);
}
