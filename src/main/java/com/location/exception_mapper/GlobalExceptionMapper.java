package com.location.exception_mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;


@Provider // VERY IMPORTANT → registers mapper automatically
public class GlobalExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        Map<String, String> errors = new HashMap<>();

        // collect validation errors
        for (ConstraintViolation<?> violation :
                exception.getConstraintViolations()) {

            String fullPath = violation.getPropertyPath().toString();
            String[] pathParts = fullPath.split("\\.");
            String fieldName = pathParts[pathParts.length - 1];
            String message = violation.getMessage();

            errors.put(fieldName, message);
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}