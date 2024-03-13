package com.example.testlogin.Controller;

import com.example.testlogin.DTO.Request.CartProductRequest;
import com.example.testlogin.DTO.Request.UserRequest;
import com.example.testlogin.Service.AuthenticationService;
import com.example.testlogin.Service.ImageService;
import com.example.testlogin.Service.ProductService;
import com.example.testlogin.Service.UserCartService;
import com.example.testlogin.Service.UsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class InformationController {

  private final AuthenticationService service;
  private final UsersService usersService;
  private final ProductService productService;
  private final UserCartService userCartService;
  private final ImageService imageService;

  @GetMapping("/welcome-profile")
  public ResponseEntity<String> sayHelloToCustomer() {
    return ResponseEntity.ok("Welcome to your profile!");
  }

  @PostMapping("/get-infor")
  public ResponseEntity<?> userGetInfor(
    @RequestHeader("Authorization") String header
  ) {
    String token = header.substring(7);

    return service.userGetInfor(token);
  }

  @PostMapping("/change-infor")
  public ResponseEntity<?> updateUserInfo(
    @RequestHeader("Authorization") String header,
    @RequestBody UserRequest request
  ) {
    return usersService.updateUserInfo(header, request);
  }

  @PostMapping("/upload-avatar")
  public ResponseEntity<?> uploadAvatar(
    @RequestHeader("Authorization") String header,
    @RequestParam(value = "image", required = false) List<MultipartFile> files
  ) {
    return imageService.addAvatar(header, files);
  }

  @PostMapping("/{seo}/addtocart")
  public ResponseEntity<?> addProductToCart(
    @RequestHeader("Authorization") String header,
    @PathVariable("seo") String seo,
    @RequestBody int quantity
  ) {
    return productService.addProductToCart(header, seo, quantity);
  }

  @PostMapping("/cart/update-quantity")
  public ResponseEntity<?> updateQuantity(
    @RequestBody CartProductRequest request
  ) {
    return userCartService.updateQuantity(
      request.getCartProductID(),
      request.getNewQuantity()
    );
  }
}
