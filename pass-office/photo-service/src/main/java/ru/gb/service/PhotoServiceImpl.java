package ru.gb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entity.Photo;
import ru.gb.repository.PhotoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Optional<Photo> getPhotoById(long id) {
        return photoRepository.findById(id);
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> updatePhoto(long id, Photo photo) {
        photoRepository.findById(id)
                .ifPresent(editPhoto -> {
                    editPhoto.setOwnerId(photo.getOwnerId());
                    editPhoto.setPhotoURL(photo.getPhotoURL());
                    photoRepository.save(editPhoto);
                });
        return photoRepository.findById(id);
    }

    @Override
    public void deletePhoto(long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

}
