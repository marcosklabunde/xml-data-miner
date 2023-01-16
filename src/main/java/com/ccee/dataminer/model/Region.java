package com.ccee.dataminer.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private Acronym acronym;

    @ElementCollection
    private List<BigDecimal> generation = new ArrayList<>();

    @ElementCollection
    private List<BigDecimal> purchase = new ArrayList<>();


    @ElementCollection
    private List<BigDecimal> averagePrice = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Acronym getAcronym() {
        return acronym;
    }

    public void setAcronym(Acronym acronym) {
        this.acronym = acronym;
    }

    public List<BigDecimal> getGeneration() {
        return generation;
    }

    public void setGeneration(List<BigDecimal> generation) {
        this.generation = generation;
    }

    public List<BigDecimal> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<BigDecimal> purchase) {
        this.purchase = purchase;
    }

    public List<BigDecimal> getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(List<BigDecimal> averagePrice) {
        this.averagePrice = averagePrice;
    }
}
