package com.example.crud.controller;

import com.example.crud.entities.Product;
import com.example.crud.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto createProductDto){
        var productId = productService.createProduct(createProductDto);

        return ResponseEntity.created(URI.create("/products" + productId.toString())).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId){
        var product = productService.getProductById(productId);
        if (product.isPresent()){
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(){
        var products = productService.listProducts();

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProductById(@PathVariable("productId") String productId,
                                                  @RequestBody UpdateProductDto updateProductDto) {
        productService.updateProduct(productId, updateProductDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable("productId") String productId){
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
