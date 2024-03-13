package com.example.testlogin.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductRequest {

  private int productID;
  private int quantity;
  private int total;
}
