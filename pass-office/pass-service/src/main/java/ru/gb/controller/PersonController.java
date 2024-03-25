package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Person;
import ru.gb.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@Tag(name = "Person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // GET /person - получить всех физических лиц
    @GetMapping
    @Operation(summary = "get all persons", description = "Получение списка всех физических лиц")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }

    // GET /person/{id} - получить физическое лицо по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a person by id", description = "Поиск физического лица по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable long id) {
        final Optional<Person> person;
        person = personService.getPersonById(id);
        if (person.isEmpty()) {
            System.out.println("Физическое лицо: не найдено");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Физическое лицо: " + personService.getPersonById(id));
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }

    // POST /person - добавить физическое лицо (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new person", description = "Добавление нового физического лица")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    // PUT /person/{id} - обновить данные физического лица (принимает JSON)
    @PutMapping("/{id}")
    @Operation(summary = "update person info by id", description = "Обновление данных физического лица по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Person> updatePersonById(@PathVariable long id, @RequestBody Person person) {
        personService.updatePerson(id, person);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    // DELETE /person/{id} - удалить физическое лицо
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a person by id", description = "Удаление физического лица по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Person> deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}