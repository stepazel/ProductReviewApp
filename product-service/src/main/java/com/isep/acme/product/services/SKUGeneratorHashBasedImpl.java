package com.isep.acme.product.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service("SKUGeneratorHashBased")
public class SKUGeneratorHashBasedImpl implements SKUGenerator {

    /**
     * Generates a SKU based on the product name.
     * product name -> SHA-256 -> hexadecimal -> 12 middle values
     *
     * @param productName The product name
     * @return Returns 12 characters that are the middle of the hexadecimal representation of the SHA-256 hash of the
     * product name
     */
    @Override
    public String generateNew(String productName) {
        try {
            byte[] hashBytes = generateSHA256Hash(productName);
            String hexString = convertToHexadecimal(hashBytes);
            return extractMiddle12Characters(hexString);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating SKU", e);
        }
    }

    /**
     * Generates a SHA-256 hash of the input string.
     *
     * @param input The input string
     * @return Returns the SHA-256 hash of the input string
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available
     */
    private byte[] generateSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes());
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param hashBytes The byte array
     * @return Returns the hexadecimal string
     */
    private String convertToHexadecimal(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Extracts the middle 12 characters of a string.
     *
     * @param hexString The string
     * @return Returns the middle 12 characters of the string
     */
    private String extractMiddle12Characters(String hexString) {
        int start = (hexString.length() - 12) / 2;
        return hexString.substring(start, start + 12);
    }
}
