package com.example.testlogin.DTO.Response;

import com.example.testlogin.Model.Comments;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHomeResponse {

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
  private int viewed;
  private int rated_total;
  private double rated_count;
  private String imagesurl;
  private String seo;
  private List<Comments> comments;
}
