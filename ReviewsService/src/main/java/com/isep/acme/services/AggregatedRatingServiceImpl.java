package com.isep.acme.services;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.AggregatedRatingRepository;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService {

    @Autowired
    @Qualifier("AggregatedRatingRepositoryAlias")
    AggregatedRatingRepository arRepository;
    @Autowired
    ReviewService              rService;
    @Autowired
    ProductService             productService;
    @Autowired
    @Qualifier("ProductRepositoryAlias")
    private ProductRepository pRepository;

    @Override
    public AggregatedRating save(String sku) {

        Optional<Product> product = pRepository.findBySku(sku);

        if (product.isEmpty()) {
            return null;
        }

        Double average = rService.getWeightedAverage(product.get());


        Optional<AggregatedRating> r = arRepository.findByProductId(product.get());
        AggregatedRating           aggregateF;

        if (r.isPresent()) {
            r.get().setAverage(average);
            aggregateF = arRepository.save(r.get());
        } else {
            AggregatedRating f = new AggregatedRating(average, product.get());
            aggregateF = arRepository.save(f);
        }

        return aggregateF;
    }


}
