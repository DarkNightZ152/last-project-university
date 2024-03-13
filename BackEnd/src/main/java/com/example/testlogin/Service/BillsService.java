package com.example.testlogin.Service;

import com.example.testlogin.DTO.Request.UpdateBillRequest;
import com.example.testlogin.Model.BillProduct;
import com.example.testlogin.Model.Bills;
import com.example.testlogin.Model.Products;
import com.example.testlogin.Repository.BillsRepo;
import com.example.testlogin.Repository.ProductsRepo;
import com.example.testlogin.Repository.StatusRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillsService {

  private final BillsRepo billsRepo;
  private final StatusRepo statusRepo;
  private final ProductsRepo productsRepo;

  //Hiển thị toàn bộ đơn hàng
  public ResponseEntity<?> returnBills() {
    return ResponseEntity.ok(billsRepo.findAll(Sort.by("ID").descending()));
  }

  //Thay đổi trạng thái đơn hàng
  public ResponseEntity<String> updateBill(
    int billID,
    UpdateBillRequest updateBillRequest
  ) {
    Optional<Bills> billOptional = billsRepo.findById(billID);
    if (billOptional.isPresent()) {
      Bills bill = billOptional.get();
      if (
        bill.getStatus().getStatus() == "Đã hủy" ||
        bill.getStatus().getStatus() == "Hoàn thành" ||
        bill.getStatus().getStatus() == "Trả hàng"
      ) {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Trạng thái đơn hàng hiện tại không cho phép thay đổi nữa!");
      } else {
        bill.setStatus(
          statusRepo.findByStatus(updateBillRequest.getStatus()).get()
        );
        bill.setReason(updateBillRequest.getReason());

        if (updateBillRequest.getStatus() == "Hoàn thành") {
          for (BillProduct billProduct : bill.getBillProduct()) {
            Products product = billProduct.getProduct();
            product.setAmount(product.getAmount() - billProduct.getQuantity());
            productsRepo.save(product);
          }
        }

        billsRepo.save(bill);

        return ResponseEntity.ok("Cập nhật trạng thái đơn hàng thành công!");
      }
    } else {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Không tìm thấy đơn hàng!");
    }
  }

  //Hiển thị toàn bộ Status
  public ResponseEntity<?> returnAllStatus() {
    return ResponseEntity.ok(statusRepo.findAll());
  }
}
