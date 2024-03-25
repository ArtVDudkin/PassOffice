package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.controller.requests.IssuePassPersonalVehicleRequest;
import ru.gb.entity.*;
import ru.gb.repository.PassPersonalVehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IssuePassPersonalVehicleServiceImpl implements IssuePassPersonalVehicleService {

    private final PersonService personService;
    private final VehicleService vehicleService;
    private final PassPersonalVehicleRepository passPersonalVehicleRepository;

    @Autowired
    public IssuePassPersonalVehicleServiceImpl(PersonService personService, VehicleService vehicleService, PassPersonalVehicleRepository passPersonalVehicleRepository) {
        this.personService = personService;
        this.vehicleService = vehicleService;
        this.passPersonalVehicleRepository = passPersonalVehicleRepository;
    }

    @Override
    public PassPersonalVehicle addIssue(IssuePassPersonalVehicleRequest request) {
        if (personService.getPersonById(request.getPersonId()).isEmpty()) {
            throw new NoSuchElementException("Не найдено физ.лицо с идентификатором \"" + request.getPersonId() + "\"");
        }
        if (vehicleService.getVehicleById(request.getVehicleId()).isEmpty()) {
            throw new NoSuchElementException("Не найдено транспортное средство с идентификатором \"" + request.getVehicleId() + "\"");
        }
        Person person = personService.getPersonById(request.getPersonId()).get();
        Vehicle vehicle = vehicleService.getVehicleById(request.getVehicleId()).get();

        PassPersonalVehicle passPersonalVehicle = new PassPersonalVehicle(vehicle, person, request.getPassGroup(), request.getValidUntil(),
                request.getCardUID(), request.getCardNo(), request.getCiphers(), request.getAllowedFacilities());
        passPersonalVehicleRepository.save(passPersonalVehicle);
        return passPersonalVehicle;
    }

    @Override
    public Optional<PassPersonalVehicle> getIssueById(long id) {
        return passPersonalVehicleRepository.findById(id);
    }

    @Override
    public List<PassPersonalVehicle> getIssuesByPerson(long id) {
        return passPersonalVehicleRepository.findByPersonId(id);
    }

    @Override
    public List<PassPersonalVehicle> getAllIssues() {
        return passPersonalVehicleRepository.findAll();
    }

    @Override
    public Optional<PassPersonalVehicle> returnPass(long id) {
        passPersonalVehicleRepository.findById(id)
                .ifPresent(issue1 -> {
                    issue1.setReturnedAt(LocalDateTime.now());
                    passPersonalVehicleRepository.save(issue1);
                });
        return passPersonalVehicleRepository.findById(id);
    }

}
