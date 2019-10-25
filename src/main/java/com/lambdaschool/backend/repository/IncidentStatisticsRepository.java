package com.lambdaschool.backend.repository;

import com.lambdaschool.backend.models.IncidentStatistics;
import org.springframework.data.repository.CrudRepository;

public interface IncidentStatisticsRepository extends CrudRepository <IncidentStatistics, Long>
{

}
