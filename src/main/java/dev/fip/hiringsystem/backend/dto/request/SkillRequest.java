package dev.fip.hiringsystem.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SkillRequest {
    @NotBlank(message = "Name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name is invalid")
    private String name;
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Please enter a valid integer")
    @Positive(message = "invalid experience")
    private int experience;
    private String docName;
}
