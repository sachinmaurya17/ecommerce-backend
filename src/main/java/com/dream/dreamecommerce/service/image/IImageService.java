package com.dream.dreamecommerce.service.image;

import com.dream.dreamecommerce.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    Image saveImage(MultipartFile file, Image image);

    void updateImage(MultipartFile file, Long id);

}
