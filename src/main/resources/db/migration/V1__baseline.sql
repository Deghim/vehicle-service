CREATE TABLE vehicles (
    id                  VARCHAR(64) NOT NULL,
    plate               VARCHAR(20) NOT NULL,
    model               VARCHAR(80),
    last_maintenance_on DATE,
    next_maintenance_on DATE,
    CONSTRAINT vehicles_pkey PRIMARY KEY (id),
    CONSTRAINT vehicles_plate_key UNIQUE (plate)
);

CREATE TABLE vehicle_telemetry (
    vehicle_id                VARCHAR(64) NOT NULL,
    engine_status             VARCHAR(40),
    battery_status_percentage NUMERIC(5,2),
    latitude                  NUMERIC(9,6),
    longitude                 NUMERIC(9,6),
    recorded_at               TIMESTAMP(6) WITH TIME ZONE,
    CONSTRAINT vehicle_telemetry_pkey PRIMARY KEY (vehicle_id),
    CONSTRAINT vehicle_telemetry_vehicle_id_fkey FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);
