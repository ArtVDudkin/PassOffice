package ru.gb.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.entity.Vehicle;
import ru.gb.repository.VehicleRepositoryTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class VehicleControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    VehicleRepositoryTest vehicleRepositoryTest;

    @Data
    @NoArgsConstructor
    static class JUnitVehicleResponse {
        private long id;
        private String brand;
        private String model;
        private String regSign;
        private String color;
    }

    @Test
    void getAllVehicles() {

        // подготовка данных
        vehicleRepositoryTest.saveAll(List.of(
                new Vehicle(1L,"bmw", "x3", "a777aa777", "black"),
                new Vehicle(2L,"toyota", "corolla", "e111ee111", "blue")
        ));

        // получение данных
        List<Vehicle> expected = vehicleRepositoryTest.findAll();

        List<JUnitVehicleResponse> responseBody = webTestClient.get()
                .uri("/vehicle")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitVehicleResponse>>() {
                })
                .returnResult()
                .getResponseBody();
        // проверка полученных данных:
        // размер ожидаемого списка автомобилей и полученного совпадает
        Assertions.assertEquals(expected.size(), responseBody.size());
        // проверяем каждый элемент списка: id совпадают и госномера автомобилей совпадают
        for (JUnitVehicleResponse vehicleResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), vehicleResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getRegSign(), vehicleResponse.getRegSign()));
            Assertions.assertTrue(found);
        }

    }
  
}