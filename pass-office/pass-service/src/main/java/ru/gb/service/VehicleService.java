package ru.gb.service;

import ru.gb.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Optional<Vehicle> getVehicleById(long id);

    Vehicle addVehicle(Vehicle vehicle);

    Optional<Vehicle> updateVehicle(long id, Vehicle vehicle);

    void deleteVehicle(long id);

    List<Vehicle> getAllVehicles();

}
