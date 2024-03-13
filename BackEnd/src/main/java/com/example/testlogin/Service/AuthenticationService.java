package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.DTO.Request.AuthenticationRequest;
import com.example.testlogin.DTO.Request.RegisterRequest;
import com.example.testlogin.DTO.Response.AuthenticationResponse;
import com.example.testlogin.Model.Cart;
import com.example.testlogin.Model.Roles;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.UsersRepo;
import java.util.*;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UsersRepo usersRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  // private final CartRepo cartRepo;

  public AuthenticationResponse register(RegisterRequest request) {
    var cart = Cart.builder().cartProducts(null).build();
    var user = Users
      .builder()
      .username(request.getUsername())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(Roles.CUSTOMER)
      .cart(cart)
      .build();

    var jwtToken = jwtService.generateUserToken(user);

    usersRepo.save(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public ResponseEntity<?> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );
    Optional<Users> user = usersRepo.findByUsername(request.getUsername());
    if (user.isPresent()) {
      var jwtToken = jwtService.generateUserToken(user.get());
      return ResponseEntity.ok(jwtToken);
    } else {
      return ResponseEntity.badRequest().body("Sai tài khoản hoặc mật khẩu!");
    }
  }

  public ResponseEntity<?> userGetInfor(String token) {
    String username = jwtService.extractUsername(token);
    Users user = usersRepo
      .findByUsername(username)
      .orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng")
      );

    return ResponseEntity.ok(user);
  }
}
