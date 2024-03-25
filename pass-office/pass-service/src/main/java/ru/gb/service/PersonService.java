package ru.gb.service;

import ru.gb.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> getPersonById(long id);

    Person addPerson(Person person);

    Optional<Person> updatePerson(long id, Person person);

    void deletePerson(long id);

    List<Person> getAllPersons();

}
