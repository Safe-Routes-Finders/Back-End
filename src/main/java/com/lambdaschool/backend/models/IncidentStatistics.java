package com.lambdaschool.backend.models;

import com.lambdaschool.backend.logging.Loggable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Loggable
@ApiModel(value = "IncidentStatistics", description = "Statistic that a traffic incident")
@Entity
@Table(name = "incidentstatistics")
public class IncidentStatistics
{
    @ApiModelProperty(name = "incedentstatisticid", value = "Primary Incident Statistic ID", required = true, example =
            "2")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long incedentstatisticid;


    @ApiModelProperty(name = "incidentchance", value = "Probability that an accident will occur at a location",
            example = "0.43")
    @Column
    private float incidentchance;


    public IncidentStatistics() {
    }

    public IncidentStatistics(float incidentchance) {
        this.incidentchance = incidentchance;
    }

    public long getIncedentstatisticid() {
        return incedentstatisticid;
    }

    public void setIncedentstatisticid(long incedentstatisticid) {
        this.incedentstatisticid = incedentstatisticid;
    }

    public float getIncidentchance() {
        return incidentchance;
    }

    public void setIncidentchance(float incidentchance) {
        this.incidentchance = incidentchance;
    }

    @Override
    public String toString() {
        return "IncidentStatistics{" +
                "incedentstatisticid=" + incedentstatisticid +
                ", incidentchance=" + incidentchance +
                '}';
    }
}
