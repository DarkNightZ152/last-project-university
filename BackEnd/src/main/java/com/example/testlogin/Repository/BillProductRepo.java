package com.example.testlogin.Repository;

import com.example.testlogin.Model.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProductRepo extends JpaRepository<BillProduct, Integer> {}
