package com.eduardoschelive.digiobackend.infrastructure.config;

import com.eduardoschelive.digiobackend.infrastructure.dto.ApplicationExceptionDTO;
import com.eduardoschelive.digiobackend.infrastructure.exception.ApplicationException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationError(ApplicationException applicationError) {
        var httpStatus = applicationError.getHttpStatus();
        var responseBody = new ApplicationExceptionDTO(
                httpStatus.value(),
                applicationError.getErrorCode(),
                applicationError.getMessage(),
                Instant.now(),
                httpServletRequest.getRequestURI()
        );

        return ResponseEntity.status(httpStatus).body(responseBody);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<Object> handleCircuitBreakerError() {
        var responseBody = new ApplicationExceptionDTO(
                503,
                "CIRCUIT_BREAKER_ERROR",
                "O serviço está temporariamente indisponível, por favor, tente novamente mais tarde",
                Instant.now(),
                httpServletRequest.getRequestURI()
        );

        return ResponseEntity.status(503).body(responseBody);
    }

}
