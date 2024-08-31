package com.dream.dreamecommerce.service.product;

import com.dream.dreamecommerce.entity.Product;
import com.dream.dreamecommerce.request.AddProductRequest;
import com.dream.dreamecommerce.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);

    void deleteProductById(Product product);

    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();


    List<Product> getAllProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
