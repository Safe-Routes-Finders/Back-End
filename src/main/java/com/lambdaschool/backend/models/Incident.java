package com.lambdaschool.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.backend.logging.Loggable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@ApiModel(value = "incident", description = "traffic incidents")
@Entity
@Table(name = "incidents")
public class Incident
{
//    @ApiModelProperty(name = "incedentid", value = "Primary Incident ID", required = true, example = "2")
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long incedentid;

    @Id
    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private int yr_2010;

    @Column(nullable = true)
    private int yr_2011;

    @Column(nullable = true)
    private int yr_2012;

    @Column(nullable = true)
    private int yr_2013;

    @Column(nullable = true)
    private int yr_2014;

    @Column(nullable = true)
    private int yr_2015;

    @Column(nullable = true)
    private int yr_2016;

    @Column(nullable = true)
    private int yr_2017;

    @Column(nullable = true)
    private int yr_2018;

    @Column(nullable = true)
    private int yr_2019;

    @ApiModelProperty(name = "allyears", value = "Total Incidents in a year", example = "20")
    @Column(nullable = true)
    private int all_years;

    @Column(nullable = true)
    private int jan;

    @Column(nullable = true)
    private int feb;

    @Column(nullable = true)
    private int mar;

    @Column(nullable = true)
    private int apr;

    @Column(nullable = true)
    private int may;

    @Column(nullable = true)
    private int jun;

    @Column(nullable = true)
    private int jul;

    @Column(nullable = true)
    private int aug;

    @Column(nullable = true)
    private int sep;

    @Column(nullable = true)
    private int oct;

    @Column(nullable = true)
    private int nov;

    @Column(nullable = true)
    private int dec;

    @ApiModelProperty(name = "latitude", value = "Incident Latitude", example = "33.3427")
    @Column(nullable = true)
    private double latitude;

    @ApiModelProperty(name = "longitude", value = "Incident Longitude", example = "-32.3427")
    @Column(nullable = true)
    private double longitude;

    @ManyToMany
    @JoinTable(name = "savedIncidents", joinColumns = {@JoinColumn(name = "incedentid")}, inverseJoinColumns =
            {@JoinColumn(name = "userid")})
    @JsonIgnoreProperties("incidents")
    private List<User> users = new ArrayList<>();

    public Incident() {
    }

    public Incident(String location, int yr_2010, int yr_2011, int yr_2012, int yr_2013, int yr_2014, int yr_2015, int yr_2016, int yr_2017, int yr_2018, int yr_2019, int all_years, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct, int nov, int dec, double latitude, double longitude) {
        this.location = location;
        this.yr_2010 = yr_2010;
        this.yr_2011 = yr_2011;
        this.yr_2012 = yr_2012;
        this.yr_2013 = yr_2013;
        this.yr_2014 = yr_2014;
        this.yr_2015 = yr_2015;
        this.yr_2016 = yr_2016;
        this.yr_2017 = yr_2017;
        this.yr_2018 = yr_2018;
        this.yr_2019 = yr_2019;
        this.all_years = all_years;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    public long getIncedentid() {
//        return incedentid;
//    }
//
//    public void setIncedentid(long incedentid) {
//        this.incedentid = incedentid;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYr_2010() {
        return yr_2010;
    }

    public void setYr_2010(int yr_2010) {
        this.yr_2010 = yr_2010;
    }

    public int getYr_2011() {
        return yr_2011;
    }

    public void setYr_2011(int yr_2011) {
        this.yr_2011 = yr_2011;
    }

    public int getYr_2012() {
        return yr_2012;
    }

    public void setYr_2012(int yr_2012) {
        this.yr_2012 = yr_2012;
    }

    public int getYr_2013() {
        return yr_2013;
    }

    public void setYr_2013(int yr_2013) {
        this.yr_2013 = yr_2013;
    }

    public int getYr_2014() {
        return yr_2014;
    }

    public void setYr_2014(int yr_2014) {
        this.yr_2014 = yr_2014;
    }

    public int getYr_2015() {
        return yr_2015;
    }

    public void setYr_2015(int yr_2015) {
        this.yr_2015 = yr_2015;
    }

    public int getYr_2016() {
        return yr_2016;
    }

    public void setYr_2016(int yr_2016) {
        this.yr_2016 = yr_2016;
    }

    public int getYr_2017() {
        return yr_2017;
    }

    public void setYr_2017(int yr_2017) {
        this.yr_2017 = yr_2017;
    }

    public int getYr_2018() {
        return yr_2018;
    }

    public void setYr_2018(int yr_2018) {
        this.yr_2018 = yr_2018;
    }

    public int getYr_2019() {
        return yr_2019;
    }

    public void setYr_2019(int yr_2019) {
        this.yr_2019 = yr_2019;
    }

    public int getAll_years() {
        return all_years;
    }

    public void setAll_years(int all_years) {
        this.all_years = all_years;
    }

    public int getJan() {
        return jan;
    }

    public void setJan(int jan) {
        this.jan = jan;
    }

    public int getFeb() {
        return feb;
    }

    public void setFeb(int feb) {
        this.feb = feb;
    }

    public int getMar() {
        return mar;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }

    public int getApr() {
        return apr;
    }

    public void setApr(int apr) {
        this.apr = apr;
    }

    public int getMay() {
        return may;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public int getJun() {
        return jun;
    }

    public void setJun(int jun) {
        this.jun = jun;
    }

    public int getJul() {
        return jul;
    }

    public void setJul(int jul) {
        this.jul = jul;
    }

    public int getAug() {
        return aug;
    }

    public void setAug(int aug) {
        this.aug = aug;
    }

    public int getSep() {
        return sep;
    }

    public void setSep(int sep) {
        this.sep = sep;
    }

    public int getOct() {
        return oct;
    }

    public void setOct(int oct) {
        this.oct = oct;
    }

    public int getNov() {
        return nov;
    }

    public void setNov(int nov) {
        this.nov = nov;
    }

    public int getDec() {
        return dec;
    }

    public void setDec(int dec) {
        this.dec = dec;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Incident{" +
//                "incedentid=" + incedentid +
                ", location='" + location + '\'' +
                ", yr_2010=" + yr_2010 +
                ", yr_2011=" + yr_2011 +
                ", yr_2012=" + yr_2012 +
                ", yr_2013=" + yr_2013 +
                ", yr_2014=" + yr_2014 +
                ", yr_2015=" + yr_2015 +
                ", yr_2016=" + yr_2016 +
                ", yr_2017=" + yr_2017 +
                ", yr_2018=" + yr_2018 +
                ", yr_2019=" + yr_2019 +
                ", all_years=" + all_years +
                ", jan=" + jan +
                ", feb=" + feb +
                ", mar=" + mar +
                ", apr=" + apr +
                ", may=" + may +
                ", jun=" + jun +
                ", jul=" + jul +
                ", aug=" + aug +
                ", sep=" + sep +
                ", oct=" + oct +
                ", nov=" + nov +
                ", dec=" + dec +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
