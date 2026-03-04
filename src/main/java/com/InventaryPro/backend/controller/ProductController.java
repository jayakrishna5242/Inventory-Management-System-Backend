package com.InventaryPro.backend.controller;

import com.InventaryPro.backend.model.Product;
import com.InventaryPro.backend.repository.ProductRepository;
import com.InventaryPro.backend.repository.PurchaseRepository;
import com.InventaryPro.backend.repository.SaleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin

public class ProductController {

    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;
    private final SaleRepository saleRepo;
    public ProductController(ProductRepository productRepo,
                               PurchaseRepository purchaseRepo,
                               SaleRepository saleRepo) {

        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.saleRepo = saleRepo;
    }

    @GetMapping
    public List<Product> getAll(){
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return productRepo.findAllCategories();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productRepo.save(product);
    }



    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product updated){

        Product product = productRepo.findById(id).orElseThrow();

        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setPrice(updated.getPrice());
        product.setQuantity(updated.getQuantity());
        product.setReorderLevel(updated.getReorderLevel());

        return productRepo.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productRepo.deleteById(id);
    }
}