package com.dream.dreamecommerce.request;

import com.dream.dreamecommerce.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;

    private BigDecimal price;
    private int inventory;
    private String description;

    private Category category;
}
