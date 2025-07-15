package com.anchal.blogApp.Model.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class TagDto {

    @NotBlank(message = "name cannot be null or empty")
    @Size(min = 2 , max = 15, message = "name should be b/w 2-15 char long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "name should be alphanumeric")
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long postCount;

}
