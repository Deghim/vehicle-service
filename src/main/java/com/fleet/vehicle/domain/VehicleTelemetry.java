package com.fleet.vehicle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "vehicle_telemetry")
public class VehicleTelemetry {
    @Id @Column(name = "vehicle_id", length = 64)
    private String vehicleId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "vehicle_id")
    private VehicleModel vehicle;
    @Column(length = 40)
    private String engineStatus;
    @Column(precision = 5, scale = 2)
    private BigDecimal batteryStatusPercentage;
    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;
    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;
    private Instant recordedAt;

    protected VehicleTelemetry() {
    }

    public VehicleTelemetry(String engineStatus, BigDecimal batteryStatusPercentage, BigDecimal latitude, BigDecimal longitude, Instant recordedAt) {
        this.engineStatus = engineStatus;
        this.batteryStatusPercentage = batteryStatusPercentage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recordedAt = recordedAt;
    }

    void setVehicle(VehicleModel vehicle) { this.vehicle = vehicle; }
    public String getEngineStatus() { return engineStatus; }
    public BigDecimal getBatteryStatusPercentage() { return batteryStatusPercentage; }
    public BigDecimal getLatitude() { return latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public Instant getRecordedAt() { return recordedAt; }
}
