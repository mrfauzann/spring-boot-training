package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductEntity> getProducts() {
        //TODO: Add code to get all product list here
        return productService.fetchAll();
    }

    @GetMapping("{id}")
    public CommonResponse getProduct(@PathVariable("id") String id) {
        //TODO: Add code to get product here
        return new CommonResponse("Dummy Product");
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto) {
        //TODO: Code to post has added here
        return productService.add(productDto);
        //return new CommonResponse("Successfully add new product");
    }

    @PutMapping("/stock")
    public CommonResponse updateStock() {
        //TODO: Add code to post here
        return new CommonResponse("Successfully update stock");
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") String id) {
        productService.delete(Long.parseLong(id));
        return new CommonResponse("Successfully delete product");
    }
}
