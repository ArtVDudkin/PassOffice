package ru.gb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Photo;
import ru.gb.service.PhotoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/photo")
@Tag(name = "Photo")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    // GET /photo - получить все фотографии
    @GetMapping
    @Operation(summary = "get all photos", description = "Получение списка всех фотографий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<Photo>> getAllPhoto() {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.getAllPhotos());
    }

    // GET /photo/{id} - получить фото по ID
    @GetMapping("/{id}")
    @Operation(summary = "get a photo by id", description = "Поиск фото по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Optional<Photo>> getPhotoById(@PathVariable long id) {
        final Optional<Photo> photo;
        photo = photoService.getPhotoById(id);
        if (photo.isEmpty()) {
            System.out.println("Фотография: не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Фотография: " + photoService.getPhotoById(id));
            return ResponseEntity.status(HttpStatus.OK).body(photo);
        }
    }

    // POST /photo - добавить фотографию (принимает JSON)
    @PostMapping
    @Operation(summary = "add a new photo", description = "Добавление новой фотографии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo) {
        photoService.addPhoto(photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(photo);
    }

    // PUT /photo/{id} - обновить данные фотографии (принимает JSON)
    @PutMapping("/{id}")
    @Operation(summary = "update photo info by id", description = "Обновление данных фотографии по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Photo> updatePhotoById(@PathVariable long id, @RequestBody Photo photo) {
        photoService.updatePhoto(id, photo);
        return ResponseEntity.status(HttpStatus.OK).body(photo);
    }

    // DELETE /photo/{id} - удалить фотографию
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a photo by id", description = "Удаление фотографии по её id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Объект с таким id не найден")
    })
    public ResponseEntity<Photo> deletePhoto(@PathVariable long id) {
        photoService.deletePhoto(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}