package com.isep.acme.repositories.implementations.document;


import com.isep.acme.model.document.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryMongo extends MongoRepository<ReviewMongo, Long> {

    @Query("{'product.sku': ?0}")
    Optional<List<ReviewMongo>> findByProduct(@Param("sku") String productSku);

    Optional<ReviewMongo> findById(Long id);

    @Query("{'user.username': ?0}")
    Optional<List<ReviewMongo>> findByUser(@Param("userName") String userName);

    @Query("{'approvalStatus': 'pending'}")
    Optional<List<ReviewMongo>> findPending();

    @Query("{'product.sku': ?0, 'approvalStatus': ?1}")
    List<ReviewMongo> findByProductSkuAndStatus(@Param("sku") String sku, @Param("approvalStatus") String status);

    @Query("{'user.userId': ?0}")
    List<ReviewMongo> findByUserId(@Param("userId") Long userId);

    @Query("{'product.productID': ?0, 'approvalStatus': ?1}")
    List<ReviewMongo> findByProductIdAndStatus(@Param("productID") Long productID, @Param("status") String status);

    @Query("{'product.productID': ?0}")
    List<ReviewMongo> findByProductId(@Param("productID") Long productID);

    @Query("{'approvalStatus': 'active'}")
    List<ReviewMongo> findActive();
}
