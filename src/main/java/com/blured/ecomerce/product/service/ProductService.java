package com.blured.ecomerce.product.service;

import com.blured.ecomerce.product.dao.ProductRepository;
import com.blured.ecomerce.product.domain.entity.Product;
import com.blured.ecomerce.product.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    @RequestMapping(value="/product/{id}", method=RequestMethod.GET)
    Product getProduct(@PathVariable("id") int id) {
        Optional<Product> product = productRepo.findById(id);

        if (!product.isPresent()) {
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND, "No product for id " + id);
        }

        return product.get();
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product savedProduct = productRepo.save(product);

        return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
    }

    @RequestMapping(value ="/product/{id}", method = RequestMethod.PUT)
    Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if (!existingProduct.isPresent()) {
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND, "Product not found for id " + id);
        }

        existingProduct.get().setCatId(product.getCatId());
        existingProduct.get().setName(product.getName());
        Product savedProduct = productRepo.save(existingProduct.get());
        return savedProduct;
    }

    @RequestMapping(value="/product/{id}", method = RequestMethod.DELETE)
    Product deleteProduct(@PathVariable("id") int id) {
        Optional<Product> product = productRepo.findById(id);

        if (!product.isPresent()) {
            String errMsg = "No product for id " + id;
            throw new BadRequestException(BadRequestException.ID_NOT_FOUND, errMsg);
        }

        productRepo.delete(product.get());
        return product.get();
    }

    @RequestMapping("/products") // For a categoryId
    List<Product> getProductsForCategory(@RequestParam("id") int id) {
        return productRepo.findByCatId(id);
    }


}
