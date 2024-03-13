package com.example.testlogin.Controller.Customer;

import com.example.testlogin.DTO.Request.OrderRequest;
import com.example.testlogin.Service.UserCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shop")
public class CartController {

  public final UserCartService userCartService;

  @GetMapping("/cart")
  public ResponseEntity<?> returnCustomerCart(
    @RequestHeader("Authorization") String token
  ) {
    return userCartService.returnCustomerCart(token);
  }

  @DeleteMapping("/cart/remove-product")
  public ResponseEntity<?> removeProductFromCart(
    @RequestParam int cartProductID
  ) {
    return userCartService.removeProductFromCart(cartProductID);
  }

  @PutMapping("/cart/update-quantity")
  public ResponseEntity<?> updateQuantity(
    @RequestParam int cartProductID,
    @RequestParam int newquantity
  ) {
    return userCartService.updateQuantity(cartProductID, newquantity);
  }

  @PostMapping("/cart/place-order")
  public ResponseEntity<?> placeOrderFromCart(
    @RequestHeader("Authorization") String header,
    @RequestBody OrderRequest orderRequest
  ) {
    return userCartService.placeOrderFromCart(header, orderRequest);
  }
}
