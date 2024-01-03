package com.isep.acme.model.jpa;


import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedRatingJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ProductJpa product;

    public AggregatedRatingJpa(double average, Product product) {
        this.average = average;
        this.product = product.toJpaModel();
    }

    public AggregatedRating toDomainEntity() {
        return new AggregatedRating(average, product.toDomainEntity());
    }
}
