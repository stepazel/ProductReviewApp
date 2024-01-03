package com.isep.acme.model;

import com.isep.acme.model.dto.ImageDTO;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Resource;
import javax.persistence.*;

@Setter
@NoArgsConstructor
public class ProdImage {

    private Long id;
    private Product product;
    private Resource image;

    public ProdImage(Product product, Resource image) {
        setProduct(product);
        //addImage(image);;
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

