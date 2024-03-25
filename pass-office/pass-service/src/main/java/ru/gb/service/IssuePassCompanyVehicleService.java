package ru.gb.service;

import ru.gb.controller.requests.IssuePassCompanyVehicleRequest;
import ru.gb.entity.PassCompanyVehicle;

import java.util.List;
import java.util.Optional;

public interface IssuePassCompanyVehicleService {

    PassCompanyVehicle addIssue(IssuePassCompanyVehicleRequest request);

    Optional<PassCompanyVehicle> getIssueById(long id);

    List<PassCompanyVehicle> getIssuesByCompany(long id);

    List<PassCompanyVehicle> getAllIssues();

    Optional<PassCompanyVehicle> returnPass(long id);

}
