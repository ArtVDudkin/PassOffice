package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.PassPersonalVehicle;

import java.util.List;

public interface PassPersonalVehicleRepository extends JpaRepository<PassPersonalVehicle,Long> {
    List<PassPersonalVehicle> findByPersonId(long personId);
}
