package com.isep.acme.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class AggregatedRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Product product;

    protected AggregatedRating() {
    }

    public AggregatedRating(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAggregatedId(Long aggregatedId) {
        this.aggregatedId = aggregatedId;
    }
}
