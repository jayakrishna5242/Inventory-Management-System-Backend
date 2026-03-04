package com.InventaryPro.backend.controller;

import com.InventaryPro.backend.model.Purchase;
import com.InventaryPro.backend.repository.PurchaseRepository;
import com.InventaryPro.backend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin

@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseRepository repo;
    private final InventoryService service;

    public PurchaseController(PurchaseRepository repo,
                              InventoryService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping
    public List<Purchase> getAll(){
        return repo.findAll();
    }

    @PostMapping
    public Purchase create(@RequestBody Map<String,Object> body){

        Long productId = Long.valueOf(body.get("productId").toString());
        int quantity = Integer.parseInt(body.get("quantity").toString());
        double price = Double.parseDouble(body.get("purchasePrice").toString());

        return service.recordPurchase(productId,quantity,price);
    }
}

