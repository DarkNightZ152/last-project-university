package com.example.testlogin.Controller;

import com.example.testlogin.DTO.Request.AuthenticationRequest;
import com.example.testlogin.DTO.Request.RegisterRequest;
import com.example.testlogin.DTO.Response.AuthenticationResponse;
import com.example.testlogin.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
    @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate") //Đây là Đăng nhập =))) Trả về chuỗi Token
  public ResponseEntity<?> authenticate(
    @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }
}
