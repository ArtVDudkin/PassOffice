package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entity.Company;
import ru.gb.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> getCompanyById(long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> updateCompany(long id, Company company) {
        companyRepository.findById(id) // returns Optional<Company>
                .ifPresent(editCompany -> {
                    editCompany.setName(company.getName());
                    editCompany.setInn(company.getInn());
                    editCompany.setPhysicalAddress(company.getPhysicalAddress());
                    editCompany.setLegalAddress(company.getLegalAddress());
                    companyRepository.save(editCompany);
                });
        return companyRepository.findById(id);
    }

    @Override
    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
}
