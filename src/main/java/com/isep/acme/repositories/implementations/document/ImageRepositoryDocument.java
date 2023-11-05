package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.ProdImage;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("imageRepositoryDocument")
public class ImageRepositoryDocument implements ImageRepository {
    @Override
    public Iterable<ProdImage> findAll() {
        return null;
    }

    @Override
    public Optional<ProdImage> findById(Long id) {
        return Optional.empty();
    }
}
