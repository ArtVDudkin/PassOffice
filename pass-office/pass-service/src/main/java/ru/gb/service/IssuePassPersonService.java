package ru.gb.service;

import ru.gb.controller.requests.IssuePassPersonRequest;
import ru.gb.entity.PassPerson;

import java.util.List;
import java.util.Optional;

public interface IssuePassPersonService {

    PassPerson addIssue(IssuePassPersonRequest request);

    Optional<PassPerson> getIssueById(long id);

    List<PassPerson> getIssuesByPerson(long id);

    List<PassPerson> getAllIssues();

    Optional<PassPerson> returnPass(long id);

}
