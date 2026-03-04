package com.InventaryPro.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String productName;

    private int quantity;

    private double purchasePrice;

    private String purchaseDate;

    // getters setters
}