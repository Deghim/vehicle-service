package com.fleet.vehicle.api;

import com.fleet.vehicle.domain.VehicleService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicles;
    public VehicleController(VehicleService vehicles) { this.vehicles = vehicles; }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody CreateVehicleRequest request) {
        VehicleResponse created = vehicles.create(request);
        return ResponseEntity.created(URI.create("/vehicles/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public VehicleResponse get(@PathVariable String id) { return vehicles.get(id); }
}
