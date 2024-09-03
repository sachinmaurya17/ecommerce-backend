package com.dream.dreamecommerce.service.image;

import com.dream.dreamecommerce.entity.Image;
import com.dream.dreamecommerce.exceptions.ResourceNotFoundException;
import com.dream.dreamecommerce.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found!"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete, () -> {
                    throw new ResourceNotFoundException("Image not found!");
                });
    }

    @Override
    public Image saveImage(MultipartFile file, Image image) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getOriginalFilename());
    }
}
