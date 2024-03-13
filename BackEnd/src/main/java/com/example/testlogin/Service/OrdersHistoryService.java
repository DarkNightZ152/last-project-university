package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.Model.Bills;
import com.example.testlogin.Model.Status;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.BillsRepo;
import com.example.testlogin.Repository.StatusRepo;
import com.example.testlogin.Repository.UsersRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersHistoryService {

  private final UsersRepo usersRepo;
  private final JwtService jwtService;
  private final BillsRepo billsRepo;
  private final StatusRepo statusRepo;

  //Hiển thị toàn bộ đơn hàng của user
  public ResponseEntity<?> returnUserBills(String header) {
    try {
      return ResponseEntity.ok(getUserByUsername(header).get().getBills());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
    }
  }

  //Hiển thị thông tin chi tiết đơn hàng của user
  public ResponseEntity<?> returnUserBill(int billID) {
    Optional<Bills> billOptional = billsRepo.findById(billID);
    if (billOptional.isPresent()) {
      return ResponseEntity.ok(billOptional.get());
    } else {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Không tìm thấy đơn hàng này trong cơ sở dữ liệu!");
    }
  }

  //Hủy đơn hàng
  public ResponseEntity<String> cancelBill(int billID) {
    Optional<Bills> billOptional = billsRepo.findById(billID);
    if (billOptional.isPresent()) {
      Bills bill = billOptional.get();
      Status cancelStatus = statusRepo.findById(2).get();
      if (bill.getStatus().getID() == cancelStatus.getID()) {
        return ResponseEntity
          .badRequest()
          .body("Không thể hủy vì đơn hàng đã hủy rồi!");
      } else {
        bill.setStatus(cancelStatus);
        billsRepo.save(bill);
        return ResponseEntity.ok("Hủy đơn hàng thành công!");
      }
    }

    return ResponseEntity.badRequest().body("Không thể tìm thấy đơn hàng này!");
  }

  /*Side function */
  private Optional<Users> getUserByUsername(String header) {
    String token = header.substring(7);
    String username = jwtService.extractUsername(token);

    return usersRepo.findByUsername(username);
  }
}
