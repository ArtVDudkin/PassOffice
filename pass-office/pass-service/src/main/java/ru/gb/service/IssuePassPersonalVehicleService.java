package ru.gb.service;

import ru.gb.controller.requests.IssuePassPersonalVehicleRequest;
import ru.gb.entity.PassPersonalVehicle;

import java.util.List;
import java.util.Optional;

public interface IssuePassPersonalVehicleService {

    PassPersonalVehicle addIssue(IssuePassPersonalVehicleRequest request);

    Optional<PassPersonalVehicle> getIssueById(long id);

    List<PassPersonalVehicle> getIssuesByPerson(long id);

    List<PassPersonalVehicle> getAllIssues();

    Optional<PassPersonalVehicle> returnPass(long id);

}
