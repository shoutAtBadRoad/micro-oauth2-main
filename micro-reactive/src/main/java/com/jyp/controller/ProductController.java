package com.jyp.controller;

import com.jyp.entity.Product;
import com.jyp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") final String id) {
        return this.productService.getProductsById(id);
    }

    @PostMapping("")
    public Mono<Void> createProduct(@RequestBody final  Mono<Product> product) {
        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Product> delete(@PathVariable("id") final String id) {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("/ceshi")
    public void ceshi() {
        Mono<Product> product = Mono.just(Product.builder().id("1").build());
        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(product);

    }

    @PostMapping("/save")
    public Mono<Product> save(@RequestBody final Product product) {
        return this.productService.save(product);
    }

    @GetMapping("/get/{id}")
    public Mono<Product> get(@PathVariable("id") String id) {
        return this.productService.get(id);
    }


}
