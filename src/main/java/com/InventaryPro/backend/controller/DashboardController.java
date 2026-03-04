package com.InventaryPro.backend.controller;

import com.InventaryPro.backend.repository.ProductRepository;
import com.InventaryPro.backend.repository.PurchaseRepository;
import com.InventaryPro.backend.repository.SaleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;
    private final SaleRepository saleRepo;

    public DashboardController(ProductRepository productRepo,
                               PurchaseRepository purchaseRepo,
                               SaleRepository saleRepo) {

        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.saleRepo = saleRepo;
    }
    @GetMapping("/recent")
    public List<Map<String,Object>> getRecent(){

        List<Map<String,Object>> activity = new ArrayList<>();


        saleRepo.findTop5ByOrderBySaleDateDesc()
                .forEach(s -> {
                    Map<String,Object> map = new HashMap<>();
                    map.put("type","SALE");
                    map.put("product",s.getProductName());
                    map.put("quantity",s.getQuantity());
                    map.put("price",s.getSellingPrice());
                    map.put("date",s.getSaleDate());
                    activity.add(map);
                });

        purchaseRepo.findTop5ByOrderByPurchaseDateDesc()
                .forEach(p -> {
                    Map<String,Object> map = new HashMap<>();
                    map.put("type","PURCHASE");
                    map.put("product",p.getProductName());
                    map.put("quantity",p.getQuantity());
                    map.put("price",p.getPurchasePrice());
                    map.put("date",p.getPurchaseDate());
                    activity.add(map);
                });

        return activity;
    }


    @GetMapping("/stats")
    public Map<String,Object> stats(){

        long totalProducts = productRepo.count();

        double revenue = saleRepo.findAll()
                .stream()
                .mapToDouble(s -> s.getQuantity()*s.getSellingPrice())
                .sum();

        double purchases = purchaseRepo.findAll()
                .stream()
                .mapToDouble(p -> p.getQuantity()*p.getPurchasePrice())
                .sum();

        int lowStock = (int) productRepo.findAll()
                .stream()
                .filter(p -> p.getQuantity() < p.getReorderLevel())
                .count();

        double profit = revenue - purchases;

        Map<String,Object> stats = new HashMap<>();

        stats.put("totalProducts",totalProducts);
        stats.put("lowStock",lowStock);
        stats.put("totalRevenue",revenue);
        stats.put("totalPurchases",purchases);
        stats.put("profit",profit);

        return stats;
    }
}
