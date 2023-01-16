package com.ccee.dataminer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Agent {

    @Id
    @GeneratedValue
    private Long id;

    private Long code;

    private Calendar date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Region> regionList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }
}
