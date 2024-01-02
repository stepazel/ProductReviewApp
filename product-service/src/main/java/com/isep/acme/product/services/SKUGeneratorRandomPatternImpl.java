package com.isep.acme.product.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("SKUGeneratorRandomPattern")
public class SKUGeneratorRandomPatternImpl implements SKUGenerator {
    private static final List<Integer> indexesThatShouldBeAlphabetical = List.of(1, 3, 5, 6, 8);
    private static final List<Integer> numericalIndexes                = List.of(0, 2, 4, 7, 9);
    private final        Random        random                          = new Random();

    /**
     * @return Returns a string of length 12 that has this pattern of letters, digits and special characters:
     * 5v4d2kc4v5/
     */
    @Override
    public String generateNew(String productName) {
        StringBuilder sku = new StringBuilder();
        for (var i = 0; i < 12; i++) {
            sku.append(getCharacterBasedOnIndex(i));
        }

        return sku.toString();
    }

    /**
     * Returns a character based on the index. The indexes that should be alphabetical are 1, 3, 5, 6, 8. The indexes
     *
     * @param i The index
     * @return Returns a character based on the index
     */
    private Character getCharacterBasedOnIndex(int i) {
        if (indexesThatShouldBeAlphabetical.contains(i))
            return (char) (random.nextInt(26) + 'a');
        if (numericalIndexes.contains(i))
            return (char) (random.nextInt(10) + '0');
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:',.<>?";
        return specialCharacters.charAt(random.nextInt(specialCharacters.length()));
    }
}
