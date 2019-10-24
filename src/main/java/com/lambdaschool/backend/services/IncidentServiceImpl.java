package com.lambdaschool.backend.services;

import com.lambdaschool.backend.models.Incident;
import com.lambdaschool.backend.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service(value = "incidentService")
public class IncidentServiceImpl implements IncidentService
{
    @Autowired
    private IncidentRepository incidentRepo;

    @Override
    public List<Incident> findAll(Pageable pageable)
    {
        List<Incident> list = new ArrayList<>();

        incidentRepo.findAll(pageable).iterator().forEachRemaining(list :: add);
        return list;
    }

    @Override
    public List<Incident> topIncident()
    {
        List<Incident> list = new ArrayList<>();
        incidentRepo.returnTopIncidents().iterator().forEachRemaining(list :: add);
        return list;
    }
//
//    public Incident(String location, int yr_2010, int yr_2011, int yr_2012, int yr_2013, int yr_2014, int yr_2015, int yr_2016, int yr_2017, int yr_2018, int yr_2019, int all_years, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct, int nov, int dec, double latitude, double longitude, ArrayList<User> users) {


    @Transactional
    @Override
    public Incident saveIncident(Incident incident) {
            Incident newIncident = new Incident();

//            Set Location
            newIncident.setLocation(incident.getLocation());
//            Set Accidents per year
            newIncident.setYr_2010(incident.getYr_2010());
            newIncident.setYr_2011(incident.getYr_2011());
            newIncident.setYr_2012(incident.getYr_2012());
            newIncident.setYr_2013(incident.getYr_2013());
            newIncident.setYr_2014(incident.getYr_2014());
            newIncident.setYr_2015(incident.getYr_2015());
            newIncident.setYr_2016(incident.getYr_2016());
            newIncident.setYr_2017(incident.getYr_2017());
            newIncident.setYr_2018(incident.getYr_2018());
            newIncident.setYr_2019(incident.getYr_2019());
//            Set Accidents of all years
            newIncident.setAll_years(incident.getAll_years());
//            set Accidents of months
            newIncident.setJan(incident.getJan());
            newIncident.setFeb(incident.getFeb());
            newIncident.setMar(incident.getMar());
            newIncident.setApr(incident.getApr());
            newIncident.setMay(incident.getMay());
            newIncident.setJun(incident.getJun());
            newIncident.setJul(incident.getJul());
            newIncident.setAug(incident.getAug());
            newIncident.setSep(incident.getSep());
            newIncident.setOct(incident.getOct());
            newIncident.setNov(incident.getNov());
            newIncident.setDec(incident.getDec());

            return incidentRepo.save(newIncident);

        }
}
