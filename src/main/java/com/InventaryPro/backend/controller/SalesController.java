package com.InventaryPro.backend.controller;

import com.InventaryPro.backend.model.Sale;
import com.InventaryPro.backend.repository.SaleRepository;
import com.InventaryPro.backend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin

public class SalesController {

    private final SaleRepository repo;
    private final InventoryService service;

    public SalesController(SaleRepository repo,
                           InventoryService service) {
        this.repo = repo;
        this.service = service;
    }

    @GetMapping
    public List<Sale> getAll(){
        return repo.findAll();
    }

    @PostMapping
    public Sale create(@RequestBody Map<String,Object> body){

        Long productId = Long.valueOf(body.get("productId").toString());
        int quantity = Integer.parseInt(body.get("quantity").toString());
        double price = Double.parseDouble(body.get("sellingPrice").toString());

        return service.recordSale(productId,quantity,price);
    }
}