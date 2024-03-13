package com.example.testlogin.Repository;

import com.example.testlogin.Model.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepo extends JpaRepository<Bills, Integer> {}
