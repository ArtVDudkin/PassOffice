package ru.gb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.Document;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Optional<Document> findBySerNo(String serNo);
}
