package com.dream.dreamecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Blob;

@Getter
@Setter
@Entity(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private Blob blob;
    private String downloadUrl;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
