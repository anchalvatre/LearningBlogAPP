package com.anchal.blogApp.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryDto {

    @NotBlank(message = "name should not be blank or null")
    @Size(min = 2, max = 50, message = "Category name should be in character range {min} and {max} ")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Category name should contain only letters, numbers , spaces and hyphens")
    private String name;
}
