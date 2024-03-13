package com.example.testlogin.DTO.Request;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  private String name;
  private Date birth;
  private String gender;
  private String address;
  private String email;
  private String phone;
  private String avatar;
}
