package com.isep.acme.tests;

import com.isep.acme.services.SKUGeneratorFirstImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SKUGeneratorTests {
    private final SKUGeneratorFirstImpl skuGeneratorFirst;

    SKUGeneratorTests() {
        skuGeneratorFirst = new SKUGeneratorFirstImpl();
    }

    @Test
    void firstGeneratorTest() {
        var sku = skuGeneratorFirst.generateNew();
        assert sku.length() == 11;

        assert Character.isLetter(sku.charAt(1));
        assert Character.isLetter(sku.charAt(3));
        assert Character.isLetter(sku.charAt(5));

        assert Character.isDigit(sku.charAt(0));
        assert Character.isDigit(sku.charAt(2));
        assert Character.isDigit(sku.charAt(4));

        assert Character.isLetter(sku.charAt(6));
        assert Character.isLetter(sku.charAt(8));

        assert Character.isDigit(sku.charAt(7));
        assert Character.isDigit(sku.charAt(9));

        assert !Character.isLetter(sku.charAt(10)) && !Character.isDigit(sku.charAt(10));
    }



}
