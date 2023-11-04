package com.isep.acme.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SKUGeneratorFirstImpl implements SKUGenerator {
    private final Random random = new Random();

    /**
     * @return Returns a string of length 12 that has this pattern of letters, digits and special characters: 5v4d2kc4v5/
     */
    @Override
    public String generateNew() {
        StringBuilder sku = new StringBuilder();
        for (var i = 0; i < 12; i++) {
            sku.append(getCharacterBasedOnIndex(i));
        }

        return sku.toString();
    }

    private Character getCharacterBasedOnIndex(int i) {
        if (indexesThatShouldBeAlphabetical.contains(i)) return (char) (random.nextInt(26) + 'a');
        if (numericalIndexes.contains(i)) return (char) (random.nextInt(10) + '0');
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:',.<>?";
        return specialCharacters.charAt(random.nextInt(specialCharacters.length()));
    }

    private static final List<Integer> indexesThatShouldBeAlphabetical = List.of(1, 3, 5, 6, 8);

    private static final List<Integer> numericalIndexes = List.of(0, 2, 4, 7, 9);
}
