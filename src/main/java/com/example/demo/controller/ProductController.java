package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStokDto;
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
    public List<ProductEntity> getProducts(@RequestParam(value = "inStock", defaultValue = "0")boolean isInStock) {
        return productService.fetch(isInStock);
    }

    @GetMapping("byPrice")
    public List<ProductEntity> getProducts(@RequestParam(value = "byPrice", defaultValue = "1000")long byPrice) {
        return productService.fetchAllInPrice(byPrice);
    }

    //yg dri price
    /*@GetMapping("")
    public List<ProductEntity> getProductsByPrice(@PathVariable("price") String maxPrice) {
        return productService.fetchAllInPrice(Long.parseLong(maxPrice));
    }*/

    /*@GetMapping("{price}")
    public List<ProductEntity> getProductByPrice(@PathVariable("price") String price) {
        return productService.getByPrice(Long.parseLong(price));
    }*/

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable("id") String id) {
        return productService.getById(Long.parseLong(id));
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto) {
        //TODO: Code to post has added here
        return productService.add(productDto);    }

    @PutMapping("/stock")
    public ProductEntity updateStock(@RequestBody UpdateStokDto request) {
        return productService.updateStock(request);
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") String id) {
        productService.delete(Long.parseLong(id));
        return new CommonResponse("Successfully delete product");
    }
}
