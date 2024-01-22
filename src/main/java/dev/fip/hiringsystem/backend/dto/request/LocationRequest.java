package dev.fip.hiringsystem.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocationRequest {
    @NotBlank(message = "Country name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "country name is invalid")
    private String country;
    @NotBlank(message = "City name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "city name is invalid")
    private String city;
    @NotBlank(message = "Address should not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9,]*$", message = "address is invalid")
    private String Address;
}
