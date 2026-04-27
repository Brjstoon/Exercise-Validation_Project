package org.example.exercisevalidation.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "ID must not be empty!")
    @Size(min = 3, message = "ID should be Greater than 2")
    private String ID;

    @NotEmpty(message = "Title must not be empty!")
    @Size(min = 9, message = "Title should be Greater than 8")
    private String title;

    @NotEmpty(message = "Description must not be empty!")
    @Size(min = 16, message = "Description should be Greater than 15")
    private String description;

    @NotEmpty
    @Pattern(regexp = "^(not started|in progress|completed)$", message = "Status must be not started or in progress or completed.")
    private String status;

    @NotEmpty(message = "Company Name must not be empty!")
    @Size(min = 7, message = "Company Name should be Greater than 6")
    private String companyName;

}
