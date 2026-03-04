package com.InventaryPro.backend.repository;

import com.InventaryPro.backend.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> findTop5ByOrderBySaleDateDesc();

}