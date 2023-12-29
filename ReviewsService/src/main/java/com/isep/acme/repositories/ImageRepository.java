package com.isep.acme.repositories;

import com.isep.acme.model.ProdImage;

import java.util.Optional;

public interface ImageRepository {
    Iterable<ProdImage> findAll();

    Optional<ProdImage> findById(Long id);
}
