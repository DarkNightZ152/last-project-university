package com.example.testlogin.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

  private String name;
  private String category;
  private String manufactory;
  private String descriptrion;
  private String detail_des;
  private int amount;
  private String quality;
  private int price;
  private int discount;
  private int guarantee;
}
