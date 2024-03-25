package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
