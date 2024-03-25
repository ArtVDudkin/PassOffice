package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.controller.requests.IssuePassCompanyVehicleRequest;
import ru.gb.entity.*;
import ru.gb.repository.PassCompanyVehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IssuePassCompanyVehicleServiceImpl implements IssuePassCompanyVehicleService {

    private final CompanyService companyService;
    private final VehicleService vehicleService;
    private final PassCompanyVehicleRepository passCompanyVehicleRepository;

    @Autowired
    public IssuePassCompanyVehicleServiceImpl(CompanyService companyService, VehicleService vehicleService, PassCompanyVehicleRepository passCompanyVehicleRepository) {
        this.companyService = companyService;
        this.vehicleService = vehicleService;
        this.passCompanyVehicleRepository = passCompanyVehicleRepository;
    }
    @Override
    public PassCompanyVehicle addIssue(IssuePassCompanyVehicleRequest request) {
        if (companyService.getCompanyById(request.getCompanyId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена организация с идентификатором \"" + request.getCompanyId() + "\"");
        }
        if (vehicleService.getVehicleById(request.getVehicleId()).isEmpty()) {
            throw new NoSuchElementException("Не найдено транспортное средство с идентификатором \"" + request.getVehicleId() + "\"");
        }
        Company company = companyService.getCompanyById(request.getCompanyId()).get();
        Vehicle vehicle = vehicleService.getVehicleById(request.getVehicleId()).get();

        PassCompanyVehicle passCompanyVehicle = new PassCompanyVehicle(vehicle, company, request.getPassGroup(), request.getValidUntil(),
                request.getCardUID(), request.getCardNo(), request.getCiphers(), request.getAllowedFacilities());
        passCompanyVehicleRepository.save(passCompanyVehicle);
        return passCompanyVehicle;
    }

    @Override
    public Optional<PassCompanyVehicle> getIssueById(long id) {
        return passCompanyVehicleRepository.findById(id);
    }

    @Override
    public List<PassCompanyVehicle> getIssuesByCompany(long id) {
        return passCompanyVehicleRepository.findByCompanyId(id);
    }

    @Override
    public List<PassCompanyVehicle> getAllIssues() {
        return passCompanyVehicleRepository.findAll();
    }

    @Override
    public Optional<PassCompanyVehicle> returnPass(long id) {
        passCompanyVehicleRepository.findById(id)
                .ifPresent(issue1 -> {
                    issue1.setReturnedAt(LocalDateTime.now());
                    passCompanyVehicleRepository.save(issue1);
                });
        return passCompanyVehicleRepository.findById(id);
    }

}
