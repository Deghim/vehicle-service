# Arquitectura de vehicle-service

## Componentes

```mermaid
flowchart LR
  client[Cliente HTTP] --> api[VehicleController]
  api --> service[VehicleService]
  service --> repo[VehicleRepository]
  repo --> db[(PostgreSQL vehicle_db)]
  db --- vehicles[vehicles]
  db --- telemetry[vehicle_telemetry]
```

## Alta de vehículo

```mermaid
sequenceDiagram
  participant C as Cliente
  participant API as VehicleController
  participant S as VehicleService
  participant DB as PostgreSQL
  C->>API: POST /vehicles
  API->>API: Validar campos
  API->>S: create(request)
  S->>DB: Insertar vehículo y telemetría
  DB-->>S: Confirmar transacción
  S-->>API: VehicleResponse
  API-->>C: 201 Created
```

`vehicles` guarda atributos estables. `vehicle_telemetry` guarda el estado actual, con una relación uno a uno. La entidad declara la placa como única en el esquema JPA.
