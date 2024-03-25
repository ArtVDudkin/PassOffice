package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entity.Vehicle;
import ru.gb.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public Optional<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> updateVehicle(long id, Vehicle vehicle) {
        vehicleRepository.findById(id) // returns Optional<Vehicle>
                .ifPresent(editVehicle -> {
                    editVehicle.setBrand(vehicle.getBrand());
                    editVehicle.setModel(vehicle.getModel());
                    editVehicle.setRegSign(vehicle.getRegSign());
                    editVehicle.setColor(vehicle.getColor());
                    vehicleRepository.save(editVehicle);
                });
        return vehicleRepository.findById(id);
    }

    @Override
    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

}
