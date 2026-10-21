package com.fleet.vehicle.api;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String id) { super("no existe el vehículo " + id ); }
}
