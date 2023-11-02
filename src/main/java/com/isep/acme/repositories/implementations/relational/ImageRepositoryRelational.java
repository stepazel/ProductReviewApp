package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.ProdImage;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("imageRepositoryRelational")
public class ImageRepositoryRelational implements ImageRepository {
    private final ImageRepositoryJPA imageRepositoryJPA;

    public ImageRepositoryRelational(ImageRepositoryJPA imageRepositoryJPA) {
        this.imageRepositoryJPA = imageRepositoryJPA;
    }

    @Override
    public Iterable<ProdImage> findAll() {
        return imageRepositoryJPA.findAll();
    }

    @Override
    public Optional<ProdImage> findById(Long id) {
        return imageRepositoryJPA.findById(id);
    }
}
