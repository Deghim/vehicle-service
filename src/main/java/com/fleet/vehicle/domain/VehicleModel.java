package com.fleet.vehicle.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import java.time.LocalDate;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "vehicles")
public class VehicleModel implements Persistable<String> {
    @Id @Column(length = 64)
    private String id;
    @Column(length = 20, unique = true, nullable = false)
    private String plate;
    @Column(length = 80)
    private String model;
    private LocalDate lastMaintenanceOn;
    private LocalDate nextMaintenanceOn;
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private VehicleTelemetry telemetry;
    @Transient
    private boolean fresh = true;

    protected VehicleModel() {
    }

    public VehicleModel(String id, String plate, String model, LocalDate lastMaintenanceOn, LocalDate nextMaintenanceOn) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.lastMaintenanceOn = lastMaintenanceOn;
        this.nextMaintenanceOn = nextMaintenanceOn;
    }

    public void setTelemetry(VehicleTelemetry telemetry) {
        this.telemetry = telemetry;
        telemetry.setVehicle(this);
    }

    @Override public String getId() { return id; }
    @Override public boolean isNew() { return fresh; }
    public String getPlate() { return plate; }
    public String getModel() { return model; }
    public LocalDate getLastMaintenanceOn() { return lastMaintenanceOn; }
    public LocalDate getNextMaintenanceOn() { return nextMaintenanceOn; }
    public VehicleTelemetry getTelemetry() { return telemetry; }
    @PostLoad @PostPersist void markPersisted() { fresh = false; }
}
