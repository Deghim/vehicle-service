package com.fleet.vehicle.api;

import org.springframework.dao.DataIntegrityViolationException;
import com.fleet.vehicle.domain.InvalidVehicleRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(VehicleNotFoundException.class)
    ProblemDetail notFound(VehicleNotFoundException error) {
        return problem(HttpStatus.NOT_FOUND, "VEHICLE_NOT_FOUND", error.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail conflict(Exception error) {
        return problem(HttpStatus.CONFLICT, "VEHICLE_CONFLICT", " la placa ya existe.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail invalid(MethodArgumentNotValidException error) {
        return problem(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "campos inválidos.");
    }

    @ExceptionHandler(InvalidVehicleRequestException.class)
    ProblemDetail invalidDomain(InvalidVehicleRequestException error) {
        return problem(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", error.getMessage());
    }

    private ProblemDetail problem(HttpStatus status, String code, String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setProperty("code", code);
        return problem;
    }
}
