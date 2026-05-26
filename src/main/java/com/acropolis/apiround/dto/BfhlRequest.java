package com.acropolis.apiround.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BfhlRequest(
        @NotNull
        List<@NotNull String> data
) {
}
