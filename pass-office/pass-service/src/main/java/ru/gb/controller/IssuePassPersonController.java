package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.controller.requests.IssuePassPersonRequest;
import ru.gb.entity.PassPerson;
import ru.gb.entity.Person;
import ru.gb.service.IssuePassPersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issue/")
@Tag(name = "IssuePerson")
public class IssuePassPersonController {

    private final IssuePassPersonService issuePassPersonService;

    @Autowired
    public IssuePassPersonController(IssuePassPersonService issuePassPersonService) {
        this.issuePassPersonService = issuePassPersonService;
    }

    // GET /person - получить список выдачи всех пропусков физических лиц
    @GetMapping
    @Operation(summary = "get all passes", description = "Получение списка выдачи всех пропусков физических лиц")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<PassPerson>> getAllPasses() {
        return ResponseEntity.status(HttpStatus.OK).body(issuePassPersonService.getAllIssues());
    }

    // GET /person/{id} - получить пропуск физического лица по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a pass of person by id", description = "Поиск пропуска физического лица по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<PassPerson>> getPassPersonById(@PathVariable long id) {
        final Optional<PassPerson> passPerson;
        passPerson = issuePassPersonService.getIssueById(id);
        if (passPerson.isEmpty()) {
            System.out.println("Пропуск физического лица: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Пропуск физического лица: " + issuePassPersonService.getIssueById(id));
            return ResponseEntity.status(HttpStatus.OK).body(passPerson);
        }
    }

    // POST /person - добавить пропуск физического лица (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new pass person", description = "Добавление нового пропуска физического лица")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<IssuePassPersonRequest> addPass(@RequestBody IssuePassPersonRequest request) {
        issuePassPersonService.addIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }


}