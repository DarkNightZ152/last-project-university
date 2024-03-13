package com.example.testlogin.Repository;

import com.example.testlogin.Model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
  Optional<Category> findBySeo(String seo);
  Optional<Category> findByName(String name);
}
