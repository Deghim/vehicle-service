# vehicle-service

Guarda vehículos y su telemetría actual en PostgreSQL. Escucha en el puerto `18081`.

## Preparar la base

Crea una base PostgreSQL llamada `vehicle_db`. La configuración predeterminada usa `localhost:5432`, el usuario `postgres` y la contraseña `root`. Puedes cambiarla con `VEHICLE_DB_URL`, `VEHICLE_DB_USER` y `VEHICLE_DB_PASSWORD`. Hibernate crea y actualiza las tablas al iniciar.

## Iniciar el servicio

```sh
./mvnw spring-boot:run
```

En Windows, ejecuta `mvnw.cmd spring-boot:run`.

## Usar la API

`POST /vehicles` crea un vehículo y su telemetría. `GET /vehicles/{id}` consulta el vehículo.

```sh
curl -i http://localhost:18081/vehicles \
  -H 'Content-Type: application/json' \
  -d '{"id":"veh-001","plate":"AB 123 CD","model":"Van X","engineStatus":"RUNNING","batteryStatusPercentage":84.5,"latitude":28.6353,"longitude":-106.0889,"lastMaintenanceOn":"2026-10-01","nextMaintenanceOn":"2027-04-01"}'
curl http://localhost:18081/vehicles/veh-001
```

Responde `400` ante datos inválidos, `404` si el vehículo no existe y `409` ante un identificador o una placa duplicados. Consulta los [diagramas de arquitectura](docs/arquitectura.md) y la API compuesta.
