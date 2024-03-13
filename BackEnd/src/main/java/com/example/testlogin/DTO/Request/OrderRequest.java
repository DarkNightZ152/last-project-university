package com.example.testlogin.DTO.Request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

  private String customer_name;
  private String email;
  private String phone;
  private String address;
  private String note;
  private List<OrderProductRequest> orderProductRequests;
}
