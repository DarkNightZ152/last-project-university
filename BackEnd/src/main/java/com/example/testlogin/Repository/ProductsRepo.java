package com.example.testlogin.Repository;

import com.example.testlogin.Model.Products;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
  Optional<Products> findBySeo(String seo);
  List<Products> findByName(String name);
  List<Products> findByCategory_seo(String seo);
  List<Products> findByNameContaining(String name);
}
