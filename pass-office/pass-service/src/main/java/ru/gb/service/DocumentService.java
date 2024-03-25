package ru.gb.service;

import ru.gb.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    Optional<Document> getDocumentById(long id);

    Document addDocument(Document document);

    Optional<Document> updateDocument(long id, Document document);

    void deleteDocument(long id);

    List<Document> getAllDocuments();

}
