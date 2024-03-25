package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entity.Document;
import ru.gb.repository.DocumentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    @Override
    public Optional<Document> getDocumentById(long id) {
        return documentRepository.findById(id);
    }

    @Override
    public Document addDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> updateDocument(long id, Document document) {
        documentRepository.findById(id) // returns Optional<Document>
                .ifPresent(editDocument -> {
                    editDocument.setType(document.getType());
                    editDocument.setSerNo(document.getSerNo());
                    editDocument.setIssuedBy(document.getIssuedBy());
                    editDocument.setIssuedAt(document.getIssuedAt());
                    documentRepository.save(editDocument);
                });
        return documentRepository.findById(id);
    }

    @Override
    public void deleteDocument(long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

}
