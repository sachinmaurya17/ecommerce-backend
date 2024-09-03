package com.dream.dreamecommerce.service.image;

import com.dream.dreamecommerce.dto.ImageDto;
import com.dream.dreamecommerce.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);

    void updateImage(MultipartFile file, Long id);

}
