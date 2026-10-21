package com.fleet.vehicle.api;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleResponse(
        String id,
        String plate,
        String model,
        String engineStatus,
        BigDecimal batteryStatusPercentage,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDate lastMaintenanceOn,
        LocalDate nextMaintenanceOn) {
}
