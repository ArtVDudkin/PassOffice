package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.PassCompany;

import java.util.List;

public interface PassCompanyRepository extends JpaRepository<PassCompany,Long> {

    List<PassCompany> findByCompanyId(long companyId);
}
