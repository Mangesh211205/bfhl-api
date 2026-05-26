package com.acropolis.apiround.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BfhlResponse(
        @JsonProperty("is_success")
        boolean success,

        @JsonProperty("user_id")
        String userId,

        String email,

        @JsonProperty("roll_number")
        String rollNumber,

        @JsonProperty("odd_numbers")
        List<String> oddNumbers,

        @JsonProperty("even_numbers")
        List<String> evenNumbers,

        List<String> alphabets,

        @JsonProperty("special_characters")
        List<String> specialCharacters,

        String sum,

        @JsonProperty("concat_string")
        String concatString
) {
}
