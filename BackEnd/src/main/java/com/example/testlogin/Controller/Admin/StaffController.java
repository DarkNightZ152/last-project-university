package com.example.testlogin.Controller.Admin;

import com.example.testlogin.DTO.Request.RegisterRequest;
import com.example.testlogin.DTO.Request.UserRequest;
import com.example.testlogin.Service.UsersManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shop")
public class StaffController {

  private final UsersManageService manageService;

  @GetMapping("/staff")
  public ResponseEntity<?> getAllStaff() {
    return ResponseEntity.ok(manageService.returnStaffs());
  }

  @PostMapping("/staff/add")
  public ResponseEntity<?> addStaff(@RequestBody RegisterRequest request) {
    return manageService.addStaff(request);
  }

  @PostMapping("/staff/{id}")
  public ResponseEntity<?> updateStaff(
    @PathVariable("id") int id,
    UserRequest request
  ) {
    return manageService.updateStaff(id, request);
  }

  @DeleteMapping("/staff/{id}")
  public ResponseEntity<?> deleteNews(@PathVariable("id") int id) {
    return manageService.removeStaff(id);
  }
}
