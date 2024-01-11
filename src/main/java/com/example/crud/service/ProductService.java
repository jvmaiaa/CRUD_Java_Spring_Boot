package com.example.crud.service;

import com.example.crud.controller.CreateProductDto;
import com.example.crud.controller.UpdateProductDto;
import com.example.crud.entities.Product;
import com.example.crud.repository.ProductRepository;
import org.hibernate.persister.collection.mutation.OperationProducer;
import org.hibernate.persister.collection.mutation.UpdateRowsCoordinator;
import org.springframework.stereotype.Service;

import java.security.URIParameter;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public UUID createProduct(CreateProductDto createProductDto){

        var entity = new Product(
                UUID.randomUUID(),
                createProductDto.name(),
                createProductDto.code(),
                createProductDto.description(),
                createProductDto.price(),
                Instant.now(),
                null);
        var productSaved = productRepository.save(entity);

        return productSaved.getProductId();
    }

    public Optional<Product> getProductById(String productId){
        return productRepository.findById(UUID.fromString(productId));
    }

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public void updateProduct (String productId,
                               UpdateProductDto updateProductDto) {
        var id = UUID.fromString(productId);

        var productEntity = productRepository.findById(id);

        if (productEntity.isPresent()){
            var product = productEntity.get();

            if (updateProductDto.name() != null){
                product.setName(updateProductDto.name());
            }
            if (updateProductDto.description() != null){
                product.setDescription(updateProductDto.description());
            }
            if (updateProductDto.price() != null){
                product.setPrice(updateProductDto.price());
            }

            productRepository.save(product);
        }
    }

    public void deleteById(String productId){
        var id = UUID.fromString(productId);
        var productExist = productRepository.existsById(id);

        if (productExist){
            productRepository.deleteById(id);
        }
    }
}
