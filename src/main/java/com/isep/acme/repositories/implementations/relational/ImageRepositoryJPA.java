package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.ProdImage;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepositoryJPA  extends CrudRepository<ProdImage, Long> {
}
