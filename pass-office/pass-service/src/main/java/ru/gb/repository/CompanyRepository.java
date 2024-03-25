package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
