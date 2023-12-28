package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.ProdImage;
import com.isep.acme.model.jpa.ProdImageJpa;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepositoryJPA extends CrudRepository<ProdImageJpa, Long> {}
