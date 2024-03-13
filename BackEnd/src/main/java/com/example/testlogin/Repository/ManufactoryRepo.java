package com.example.testlogin.Repository;

import com.example.testlogin.Model.Manufactory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactoryRepo extends JpaRepository<Manufactory, Integer> {
  Optional<Manufactory> findBySeo(String seo);
  Optional<Manufactory> findByName(String name);
}
