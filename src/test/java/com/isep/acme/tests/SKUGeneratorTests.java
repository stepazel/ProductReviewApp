package com.isep.acme.tests;

import com.isep.acme.services.SKUGeneratorHashBasedImpl;
import com.isep.acme.services.SKUGeneratorRandomPatternImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SKUGeneratorTests {
    private final SKUGeneratorRandomPatternImpl skuGeneratorFirst;
    private final SKUGeneratorHashBasedImpl     skuGeneratorSecond;

    SKUGeneratorTests() {
        skuGeneratorFirst  = new SKUGeneratorRandomPatternImpl();
        skuGeneratorSecond = new SKUGeneratorHashBasedImpl();
    }

    @Test
    void firstGeneratorTest() {
        var sku = skuGeneratorFirst.generateNew("");
        assert sku.length() == 12;

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

    @Test
    void secondGeneratorTest() {
        String sku = skuGeneratorSecond.generateNew("Table");

        // check if the length is 12
        assertEquals(12, sku.length());

        // compare with the expected value
        assertEquals("1a880773abed", sku);

        // check if the same product name generates the same SKU
        assertEquals(sku, skuGeneratorSecond.generateNew("Table"));

        String sku2 = skuGeneratorSecond.generateNew("Wardrobe");

        // check if the length is 12
        assertEquals(12, sku2.length());

        // compare with the expected value
        assertEquals("6343f7bdcec1", sku2);
    }


}
