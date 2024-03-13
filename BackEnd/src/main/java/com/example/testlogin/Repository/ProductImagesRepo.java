package com.example.testlogin.Repository;

import com.example.testlogin.Model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepo
  extends JpaRepository<ProductImages, Integer> {}
