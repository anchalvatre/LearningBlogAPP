package com.anchal.blogApp.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIErrorResponse {

    private int status;
    private String message;
    List<FieldError> errors;

    public static class FieldError{
        private String field;
        private String message;
    }
}
