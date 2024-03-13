package com.example.testlogin.Controller.Staff;

import com.example.testlogin.Service.UsersManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class CustomerController {

  private final UsersManageService usersManageService;

  @GetMapping("/get-all-customer")
  public ResponseEntity<?> getAllCustomer() {
    return ResponseEntity.ok(usersManageService.returnUsers());
  }
}
