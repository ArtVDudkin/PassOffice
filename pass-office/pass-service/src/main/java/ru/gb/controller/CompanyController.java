package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Company;
import ru.gb.service.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@Tag(name = "Company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // GET /company - получить все организации
    @GetMapping
    @Operation(summary = "get all companies", description = "Получение списка всех организаций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompany());
    }

    // GET /company/{id} - получить организацию по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a company by id", description = "Поиск организации по её id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable long id) {
        final Optional<Company> company;
        company = companyService.getCompanyById(id);
        if (company.isEmpty()) {
            System.out.println("Организация: не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Организация: " + companyService.getCompanyById(id));
            return ResponseEntity.status(HttpStatus.OK).body(company);
        }
    }

    // POST /company - добавить организацию (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new company", description = "Добавление новой организации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    // PUT /company/{id} - обновить данные организации (принимает JSON)
    @PutMapping("/{id}")
    @Operation(summary = "update company info by id", description = "Обновление данных организации по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Company> updateCompanyById(@PathVariable long id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    // DELETE /company/{id} - удалить организацию
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a company by id", description = "Удаление организации по её id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Company> deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}