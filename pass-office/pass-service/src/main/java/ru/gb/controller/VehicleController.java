package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Document;
import ru.gb.entity.Vehicle;
import ru.gb.service.VehicleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // GET /vehicle - получить список транспорта
    @GetMapping
    @Operation(summary = "get all vehicles", description = "Получение списка всего транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAllVehicles());
    }

    // GET /vehicle/{id} - получить транспорт по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a vehicle by id", description = "Поиск транспорта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<Vehicle>> getVehicleById(@PathVariable long id) {
        final Optional<Vehicle> vehicle;
        vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isEmpty()) {
            System.out.println("Транспорт: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Транспорт: " + vehicleService.getVehicleById(id));
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }
    }

    // POST /vehicle - добавить транспорт (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new vehicle", description = "Добавление нового транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    // PUT /vehicle/{id} - обновить данные транспорта (принимает JSON)
    @PutMapping("/{id}")
    @Operation(summary = "update vehicle info by id", description = "Обновление данных транспорта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable long id, @RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    // DELETE /vehicle/{id} - удалить транспорт
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a vehicle by id", description = "Удаление транспорта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}