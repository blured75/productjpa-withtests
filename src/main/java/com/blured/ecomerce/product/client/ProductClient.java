package com.blured.ecomerce.product.client;

import com.blured.ecomerce.product.domain.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ProductClient {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/client/{id}")
    @HystrixCommand(fallbackMethod = "getFallbackProduct")
    Product getProduct(@PathVariable("id") int id) {
        String s = "http://PRODUCT/product/" + id;
        Product product = restTemplate.getForObject("http://PRODUCT/product/" + id, Product.class);
        return product;
    }

    Product getFallbackProduct(@PathVariable("id") int id) {
        Product product = new Product();
        product.setCatId(0);
        product.setId(0);
        product.setName("No product");

        return product;
    }


}
