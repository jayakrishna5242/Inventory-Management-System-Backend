package com.InventaryPro.backend.service;

import com.InventaryPro.backend.model.Product;
import com.InventaryPro.backend.model.Purchase;
import com.InventaryPro.backend.model.Sale;
import com.InventaryPro.backend.repository.ProductRepository;
import com.InventaryPro.backend.repository.PurchaseRepository;
import com.InventaryPro.backend.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InventoryService {

    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;
    private final SaleRepository saleRepo;

    public InventoryService(ProductRepository productRepo,
                            PurchaseRepository purchaseRepo,
                            SaleRepository saleRepo) {
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.saleRepo = saleRepo;
    }

    @Transactional
    public Purchase recordPurchase(Long productId, int quantity, double price){

        Product product = productRepo.findById(productId)
                .orElseThrow();

        product.setQuantity(product.getQuantity()+quantity);

        productRepo.save(product);

        Purchase purchase = new Purchase();

        purchase.setProductId(productId);
        purchase.setProductName(product.getName());
        purchase.setQuantity(quantity);
        purchase.setPurchasePrice(price);
        purchase.setPurchaseDate(LocalDate.now().toString());

        return purchaseRepo.save(purchase);
    }

    @Transactional
    public Sale recordSale(Long productId, int quantity, double price){

        Product product = productRepo.findById(productId)
                .orElseThrow();

        if(product.getQuantity() < quantity){
            throw new RuntimeException("Insufficient Stock");
        }

        product.setQuantity(product.getQuantity()-quantity);

        productRepo.save(product);

        Sale sale = new Sale();

        sale.setProductId(productId);
        sale.setProductName(product.getName());
        sale.setQuantity(quantity);
        sale.setSellingPrice(price);
        sale.setSaleDate(LocalDate.now().toString());

        return saleRepo.save(sale);
    }
}