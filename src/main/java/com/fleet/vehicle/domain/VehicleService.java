package com.fleet.vehicle.domain;

import com.fleet.vehicle.api.CreateVehicleRequest;
import com.fleet.vehicle.api.VehicleNotFoundException;
import com.fleet.vehicle.api.VehicleResponse;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {
    private final VehicleRepository vehicles;
    private final Clock clock;

    public VehicleService(VehicleRepository vehicles, Clock clock) {
        this.vehicles = vehicles;
        this.clock = clock;
    }

    @Transactional
    public VehicleResponse create(CreateVehicleRequest request) {
        LocalDate today = LocalDate.now(clock);
        if (request.nextMaintenanceOn() != null && request.nextMaintenanceOn().isBefore(today)) {
            throw new InvalidVehicleRequestException("La próxima fecha de mantenimiento no puede estar en el pasado.");
        }
        if (request.lastMaintenanceOn() != null && request.nextMaintenanceOn() != null && request.nextMaintenanceOn().isBefore(request.lastMaintenanceOn())) {
            throw new InvalidVehicleRequestException("La próxima fecha debe ser posterior a la última fecha de mantenimiento.");
        }
        String id = request.id().strip();
        VehicleModel vehicle = new VehicleModel(id, request.plate().strip().toUpperCase(Locale.ROOT), request.model().strip(), request.lastMaintenanceOn(), request.nextMaintenanceOn());
        vehicle.setTelemetry(new VehicleTelemetry(request.engineStatus().strip(), request.batteryStatusPercentage(), request.latitude(), request.longitude(), clock.instant()));
        return response(vehicles.saveAndFlush(vehicle));
    }

    @Transactional(readOnly = true)
    public VehicleResponse get(String id) {
        return response(vehicles.findById(id).orElseThrow(() -> new VehicleNotFoundException(id)));
    }

    private VehicleResponse response(VehicleModel vehicle) {
        VehicleTelemetry telemetry = vehicle.getTelemetry();
        return new VehicleResponse(vehicle.getId(), vehicle.getPlate(), vehicle.getModel(), telemetry.getEngineStatus(), telemetry.getBatteryStatusPercentage(), telemetry.getLatitude(), telemetry.getLongitude(), vehicle.getLastMaintenanceOn(), vehicle.getNextMaintenanceOn());
    }
}
