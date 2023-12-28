package com.isep.acme.model.jpa;

import com.isep.acme.model.dto.ImageDTO;

import javax.annotation.Resource;
import javax.persistence.*;

@Entity
public class ProdImageJpa {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private ProductJpa product;

    @Lob
    private Resource image;


    public ProdImageJpa(ProductJpa product, Resource image) {
        setProduct(product);
        //addImage(image);;

    }


    public ProdImageJpa() {

    }

    private void setProduct(ProductJpa product) {
    }


    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

/*
    public ImageService addImage( Resource image){

        return new AddImage (this.image);
    }
*/
}

