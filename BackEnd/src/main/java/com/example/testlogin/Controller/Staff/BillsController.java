package com.example.testlogin.Controller.Staff;

import com.example.testlogin.DTO.Request.UpdateBillRequest;
import com.example.testlogin.Service.BillsService;
import com.example.testlogin.Service.OrdersHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class BillsController {

  private final BillsService billsService;
  private final OrdersHistoryService ordersHistoryService;

  @GetMapping("/bills")
  public ResponseEntity<?> getAllBills() {
    return billsService.returnBills();
  }

  @PutMapping("/bills/{id}")
  public ResponseEntity<?> updateBills(
    @PathVariable("id") int id,
    @RequestBody UpdateBillRequest request
  ) {
    return billsService.updateBill(id, request);
  }

  @GetMapping("/bills/{id}")
  public ResponseEntity<?> getUserBill(@PathVariable("id") int billID) {
    return ordersHistoryService.returnUserBill(billID);
  }

  @GetMapping("/bills/status")
  public ResponseEntity<?> getStatusData() {
    return billsService.returnAllStatus();
  }
}
