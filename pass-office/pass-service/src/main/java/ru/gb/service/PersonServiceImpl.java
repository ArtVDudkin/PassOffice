package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entity.Document;
import ru.gb.entity.Person;
import ru.gb.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override
    public Optional<Person> getPersonById(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> updatePerson(long id, Person person) {
        personRepository.findById(id) // returns Optional<Person>
                .ifPresent(editPerson -> {
                    editPerson.setSurname(person.getSurname());
                    editPerson.setName(person.getName());
                    editPerson.setPatronymic(person.getPatronymic());
                    editPerson.setBirthday(person.getBirthday());
                    editPerson.setDocument(person.getDocument());
                    editPerson.setWorkplace(person.getWorkplace());
                    editPerson.setPosition(person.getPosition());
                    editPerson.setWeight(person.getWeight());
                    editPerson.setPin(person.getPin());
                    editPerson.setNote(person.getNote());
                    editPerson.setPhotoURL(person.getPhotoURL());
                    personRepository.save(editPerson);
                });
        return personRepository.findById(id);
    }

    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

}
