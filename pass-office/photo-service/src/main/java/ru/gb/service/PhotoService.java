package ru.gb.service;

import ru.gb.entity.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {

    Optional<Photo> getPhotoById(long id);

    Photo addPhoto(Photo photo);

    Optional<Photo> updatePhoto(long id, Photo photo);

    void deletePhoto(long id);

    List<Photo> getAllPhotos();

}
