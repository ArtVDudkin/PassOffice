package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.controller.requests.IssuePassPersonRequest;
import ru.gb.entity.PassPerson;
import ru.gb.entity.Person;
import ru.gb.repository.PassPersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IssuePassPersonServiceImpl implements IssuePassPersonService {

    private final PersonService personService;
    private final PassPersonRepository passPersonRepository;

    @Autowired
    public IssuePassPersonServiceImpl(PersonService personService, PassPersonRepository passPersonRepository) {
        this.personService = personService;
        this.passPersonRepository = passPersonRepository;
    }

    @Override
    public PassPerson addIssue(IssuePassPersonRequest request) {
        if (personService.getPersonById(request.getPersonId()).isEmpty()) {
            throw new NoSuchElementException("Не найдено физ.лицо с идентификатором \"" + request.getPersonId() + "\"");
        }
        Person person = personService.getPersonById(request.getPersonId()).get();

        PassPerson passPerson = new PassPerson(person, request.getPassGroup(), request.getValidUntil(),
                request.getCardUID(), request.getCardNo(), request.getCiphers(), request.getAllowedFacilities());
        passPersonRepository.save(passPerson);
        return passPerson;
    }

    @Override
    public Optional<PassPerson> getIssueById(long id) {
        return passPersonRepository.findById(id);
    }

    @Override
    public List<PassPerson> getIssuesByPerson(long id) {
        return passPersonRepository.findByPersonId(id);
    }

    @Override
    public List<PassPerson> getAllIssues() {
        return passPersonRepository.findAll();
    }

    @Override
    public Optional<PassPerson> returnPass(long id) {
        passPersonRepository.findById(id)
                .ifPresent(issue1 -> {
                    issue1.setReturnedAt(LocalDateTime.now());
                    passPersonRepository.save(issue1);
                });
        return passPersonRepository.findById(id);
    }

}
