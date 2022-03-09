package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateStokDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity add(ProductDto request){
        //ada bisnis proses disini, utk input data ke database
        ProductEntity product = new ProductEntity();
        product.setName(request.getProductName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        //todo: save ke db
        return productRepository.save(product);
    }

    public List<ProductEntity> fetch(boolean isInStock){
        if (isInStock){
            // fecth all prod. with stock > 0
            return fetchAllInStock();
        } else {
            // fecth all
            return fetchAll();
        }
    }

    /*public List<ProductEntity> fetchByPrice(boolean isInStock){
        if (isInStock){
            // fecth all prod. with stock > 0
            return fetchAllInPrice();
        } else {
            // fecth all
            return fetchAll();
        }
    }*/

    /*public List<ProductEntity> fecthMaxPrice(long maxPrice){
        return fetchAllInPrice(maxPrice);
    }*/

    public List<ProductEntity> fetchAll(){
        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> fetchAllInStock(){
        return productRepository.findByStockGreaterThan(0); //jml 0
    }

    public List<ProductEntity> fetchAllInPrice(long maxPrice){
        return productRepository.findByPriceLessThanEqual(maxPrice);
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }

    public ProductEntity getById(long id){
       return productRepository.findById(id).orElse(new ProductEntity());
    }

    /*public List<ProductEntity> getByPrice(long price){
        return (List<ProductEntity>) productRepository.findByPriceLessThanEqual(price);
    }*/

    public ProductEntity updateStock(UpdateStokDto request) {
        //get product by Id
        ProductEntity product = getById(request.getId());

        //update Stock
        long currentStock = product.getStock();
        long updatedStok = currentStock + request.getNumberOfStock();
        product.setStock(updatedStok);

        //save to db
        return productRepository.save(product);
    }
}
