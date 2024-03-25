package ru.gb.service;

import ru.gb.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Optional<Company> getCompanyById(long id);

    Company addCompany(Company company);

    Optional<Company> updateCompany(long id, Company company);

    void deleteCompany(long id);

    List<Company> getAllCompany();
}
