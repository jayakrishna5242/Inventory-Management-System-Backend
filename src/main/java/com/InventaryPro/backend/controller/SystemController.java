package com.InventaryPro.backend.controller;

import com.InventaryPro.backend.repository.ProductRepository;
import com.InventaryPro.backend.repository.PurchaseRepository;
import com.InventaryPro.backend.repository.SaleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/system")
@CrossOrigin

public class SystemController {

    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;
    private final SaleRepository saleRepo;

    public SystemController(ProductRepository productRepo,
                            PurchaseRepository purchaseRepo,
                            SaleRepository saleRepo) {
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.saleRepo = saleRepo;
    }

    @PostMapping("/reset")
    public Map<String,String> reset(){

        saleRepo.deleteAll();
        purchaseRepo.deleteAll();
        productRepo.deleteAll();

        return Map.of("message","System reset successful");
    }
}