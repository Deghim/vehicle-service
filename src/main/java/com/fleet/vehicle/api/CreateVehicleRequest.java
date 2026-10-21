package com.fleet.vehicle.api;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateVehicleRequest(
        @NotBlank @Size(max = 64) @jakarta.validation.constraints.Pattern(regexp = "[A-Za-z0-9._-]+") String id,
        @NotBlank @Size(max = 20) String plate,
        @NotBlank @Size(max = 80) String model,
        @NotBlank @Size(max = 40) String engineStatus,
        @jakarta.validation.constraints.NotNull @DecimalMin("0") @DecimalMax("100") @Digits(integer = 3, fraction = 2) BigDecimal batteryStatusPercentage,
        @jakarta.validation.constraints.NotNull @DecimalMin("-90") @DecimalMax("90") @Digits(integer = 3, fraction = 6) BigDecimal latitude,
        @jakarta.validation.constraints.NotNull @DecimalMin("-180") @DecimalMax("180") @Digits(integer = 3, fraction = 6) BigDecimal longitude,
        @PastOrPresent LocalDate lastMaintenanceOn,
        LocalDate nextMaintenanceOn) {
}
