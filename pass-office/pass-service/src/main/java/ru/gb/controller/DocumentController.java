package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Document;
import ru.gb.service.DocumentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/document")
@Tag(name = "Document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // GET /document - получить все документы
    @GetMapping
    @Operation(summary = "get all documents", description = "Получение списка всех документов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Document>> getAllDocument() {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.getAllDocuments());
    }

    // GET /document/{id} - получить документ по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a document by id", description = "Поиск документов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<Document>> getDocumentById(@PathVariable long id) {
        final Optional<Document> document;
        document = documentService.getDocumentById(id);
        if (document.isEmpty()) {
            System.out.println("Документ: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Документ: " + documentService.getDocumentById(id));
            return ResponseEntity.status(HttpStatus.OK).body(document);
        }
    }

    // POST /document - добавить документ (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new document", description = "Добавление нового документа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        documentService.addDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(document);
    }

    // PUT /document/{id} - обновить данные документа (принимает JSON)
    @PutMapping("/{id}")
    @Operation(summary = "update document info by id", description = "Обновление данных документа по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Document> updateDocumentById(@PathVariable long id, @RequestBody Document document) {
        documentService.updateDocument(id, document);
        return ResponseEntity.status(HttpStatus.OK).body(document);
    }

    // DELETE /document/{id} - удалить документ
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a document by id", description = "Удаление документа по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Document> deleteDocument(@PathVariable long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}