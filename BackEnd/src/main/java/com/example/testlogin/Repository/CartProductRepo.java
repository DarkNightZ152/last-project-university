package com.example.testlogin.Repository;

import com.example.testlogin.Model.CartProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
  List<CartProduct> findAllByCart_ID(int ID);
}
