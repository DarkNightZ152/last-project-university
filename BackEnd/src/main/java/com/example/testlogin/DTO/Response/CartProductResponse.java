package com.example.testlogin.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse {

  private String productName;
  private String productImageUrl;
  private int quantity;
  private int totalCartProduct;
}
