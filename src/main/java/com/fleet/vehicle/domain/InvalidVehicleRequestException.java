package com.fleet.vehicle.domain;

public class InvalidVehicleRequestException extends RuntimeException {
    public InvalidVehicleRequestException(String message) { super(message); }
}
