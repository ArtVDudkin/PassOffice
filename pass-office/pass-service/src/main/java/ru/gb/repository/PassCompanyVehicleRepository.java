package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.PassCompanyVehicle;

import java.util.List;

public interface PassCompanyVehicleRepository extends JpaRepository<PassCompanyVehicle,Long> {

    List<PassCompanyVehicle> findByCompanyId(long companyId);
}
