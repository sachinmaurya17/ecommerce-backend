package com.dream.dreamecommerce.service.product;

import com.dream.dreamecommerce.entity.Category;
import com.dream.dreamecommerce.entity.Product;
import com.dream.dreamecommerce.exceptions.ProductNotFoundException;
import com.dream.dreamecommerce.repository.CategoryRepository;
import com.dream.dreamecommerce.repository.ProductRepository;
import com.dream.dreamecommerce.request.AddProductRequest;
import com.dream.dreamecommerce.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = Category.builder()
                            .name(request.getCategory().getName())
                            .build();
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .inventory(request.getInventory())
                .description(request.getDescription())
                .category(category)
                .build();
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Product product) {
        productRepository.findById(product.getId())
                .ifPresentOrElse(productRepository::delete,
                        () -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long productId) {
        return productRepository.findById(productId)
                .map((existingProduct) -> updateExistingProduct(existingProduct, product))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest updateProductRequest) {
        return Product.builder()
                .name(updateProductRequest.getName())
                .brand(updateProductRequest.getBrand())
                .price(updateProductRequest.getPrice())
                .inventory(updateProductRequest.getInventory())
                .description(updateProductRequest.getDescription())
                .category(updateProductRequest.getCategory() == null ? existingProduct.getCategory() : categoryRepository.findByName(updateProductRequest.getCategory().getName()))
                .build();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandName(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
