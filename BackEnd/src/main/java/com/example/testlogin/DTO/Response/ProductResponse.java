package com.example.testlogin.DTO.Response;

import com.example.testlogin.Model.ProductImages;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

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
  private int buyed;
  private int viewed;
  private int rated_total;
  private double rated_count;
  private List<ProductImages> images;
}
