package com.acropolis.apiround.service;

import com.acropolis.apiround.config.BfhlProperties;
import com.acropolis.apiround.dto.BfhlRequest;
import com.acropolis.apiround.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class BfhlProcessorService implements BfhlService {

    private static final Pattern INTEGER = Pattern.compile("-?\\d+");
    private static final Pattern ONLY_LETTERS = Pattern.compile("[A-Za-z]+");

    private final BfhlProperties properties;

    public BfhlProcessorService(BfhlProperties properties) {
        this.properties = properties;
    }

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        StringBuilder lettersInInputOrder = new StringBuilder();
        BigInteger sum = BigInteger.ZERO;

        for (String value : request.data()) {
            keepLetters(value, lettersInInputOrder);

            if (isInteger(value)) {
                BigInteger number = new BigInteger(value);
                sum = sum.add(number);

                if (number.testBit(0)) {
                    oddNumbers.add(value);
                } else {
                    evenNumbers.add(value);
                }
            } else if (isAlphabetic(value)) {
                alphabets.add(value.toUpperCase(Locale.ROOT));
            } else {
                specialCharacters.add(value);
            }
        }

        return new BfhlResponse(
                true,
                properties.userId(),
                properties.email(),
                properties.rollNumber(),
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                sum.toString(),
                alternatingCapsFromRight(lettersInInputOrder)
        );
    }

    private boolean isInteger(String value) {
        return INTEGER.matcher(value).matches();
    }

    private boolean isAlphabetic(String value) {
        return ONLY_LETTERS.matcher(value).matches();
    }

    private void keepLetters(String value, StringBuilder letters) {
        for (char character : value.toCharArray()) {
            if (Character.isLetter(character)) {
                letters.append(character);
            }
        }
    }

    private String alternatingCapsFromRight(StringBuilder letters) {
        StringBuilder result = new StringBuilder();

        for (int index = letters.length() - 1; index >= 0; index--) {
            char character = letters.charAt(index);
            boolean makeUppercase = (letters.length() - 1 - index) % 2 == 0;
            result.append(makeUppercase ? Character.toUpperCase(character) : Character.toLowerCase(character));
        }

        return result.toString();
    }
}
