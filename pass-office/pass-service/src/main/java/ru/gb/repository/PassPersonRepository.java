package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.PassPerson;

import java.util.List;

public interface PassPersonRepository extends JpaRepository<PassPerson,Long> {

    List<PassPerson> findByPersonId(long personId);
}
