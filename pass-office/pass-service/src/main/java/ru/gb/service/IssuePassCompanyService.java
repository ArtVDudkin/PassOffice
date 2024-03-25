package ru.gb.service;

import ru.gb.controller.requests.IssuePassCompanyRequest;
import ru.gb.entity.PassCompany;

import java.util.List;
import java.util.Optional;

public interface IssuePassCompanyService {

    PassCompany addIssue(IssuePassCompanyRequest request);

    Optional<PassCompany> getIssueById(long id);

    List<PassCompany> getIssuesByCompany(long id);

    List<PassCompany> getAllIssues();

    Optional<PassCompany> returnPass(long id);

}
