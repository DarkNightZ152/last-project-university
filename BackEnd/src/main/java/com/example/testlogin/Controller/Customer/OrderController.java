package com.example.testlogin.Controller.Customer;

import com.example.testlogin.Service.OrdersHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shop")
public class OrderController {

  private final OrdersHistoryService ordersHistoryService;

  @GetMapping("/orders")
  public ResponseEntity<?> getOrders(
    @RequestHeader("Authorization") String header
  ) {
    return ordersHistoryService.returnUserBills(header);
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<?> getUserBill(@PathVariable("id") int billID) {
    return ordersHistoryService.returnUserBill(billID);
  }

  @PutMapping("/orders/{id}")
  public ResponseEntity<?> cancelOrders(@PathVariable("id") int billID) {
    return ordersHistoryService.cancelBill(billID);
  }
}
