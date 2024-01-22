package dev.fip.hiringsystem.backend.dto.request;

import dev.fip.hiringsystem.backend.model.Role;
import dev.fip.hiringsystem.backend.utils.EnumValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HiringRequestDTO {
    @NotBlank(message = "job title cannot be left blank")
    private String jobTitle;
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Please enter a valid integer")
    @Positive(message = "invalid salary")
    private int salary;
    @NotNull(message = "the role is null")
    @EnumValidation(enumClass = Role.class, message = "invalid role")
    private String role;
    @NotNull(message = "location cannot be null")
    private LocationRequest location;
    @NotEmpty(message = "skills are empty")
    private List<SkillRequest> skills;

}
