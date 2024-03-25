package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.controller.requests.IssuePassCompanyRequest;
import ru.gb.entity.Company;
import ru.gb.entity.PassCompany;
import ru.gb.entity.Person;
import ru.gb.repository.PassCompanyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IssuePassCompanyServiceImpl implements IssuePassCompanyService {

    private final CompanyService companyService;
    private final PersonService personService;
    private final PassCompanyRepository passCompanyRepository;

    @Autowired
    public IssuePassCompanyServiceImpl(CompanyService companyService, PersonService personService, PassCompanyRepository passCompanyRepository) {
        this.companyService = companyService;
        this.personService = personService;
        this.passCompanyRepository = passCompanyRepository;
    }

    @Override
    public PassCompany addIssue(IssuePassCompanyRequest request) {
        if (companyService.getCompanyById(request.getCompanyId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена организация с идентификатором \"" + request.getCompanyId() + "\"");
        }
        if (personService.getPersonById(request.getPersonId()).isEmpty()) {
            throw new NoSuchElementException("Не найдено физ.лицо с идентификатором \"" + request.getPersonId() + "\"");
        }
        Company company = companyService.getCompanyById(request.getCompanyId()).get();
        Person person = personService.getPersonById(request.getPersonId()).get();

        PassCompany passCompany = new PassCompany(company, person, request.getPassGroup(), request.getValidUntil(),
                request.getCardUID(), request.getCardNo(), request.getCiphers(), request.getAllowedFacilities());
        passCompanyRepository.save(passCompany);
        return passCompany;
    }

    @Override
    public Optional<PassCompany> getIssueById(long id) {
        return passCompanyRepository.findById(id);
    }

    @Override
    public List<PassCompany> getIssuesByCompany(long id) {
        return passCompanyRepository.findByCompanyId(id);
    }

    @Override
    public List<PassCompany> getAllIssues() {
        return passCompanyRepository.findAll();
    }

    @Override
    public Optional<PassCompany> returnPass(long id) {
        passCompanyRepository.findById(id)
                .ifPresent(issue1 -> {
                    issue1.setReturnedAt(LocalDateTime.now());
                    passCompanyRepository.save(issue1);
                });
        return passCompanyRepository.findById(id);
    }

}
