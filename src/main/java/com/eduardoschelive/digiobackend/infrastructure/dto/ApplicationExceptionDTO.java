package com.eduardoschelive.digiobackend.infrastructure.dto;

import java.time.Instant;

public record ApplicationExceptionDTO(
        Integer status,
        String error,
        String message,
        Instant timestamp,
        String path
) {
}