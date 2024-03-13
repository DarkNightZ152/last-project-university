package com.example.testlogin.Service;

import com.example.testlogin.DTO.Request.RegisterRequest;
import com.example.testlogin.DTO.Request.UserRequest;
import com.example.testlogin.Model.Cart;
import com.example.testlogin.Model.Roles;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.UsersRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersManageService {

  private final UsersRepo usersRepo;
  private final PasswordEncoder passwordEncoder;

  //Trả về danh sách người dùng
  public List<Users> returnUsers() {
    return usersRepo
      .findAll(Sort.by("ID").descending())
      .stream()
      .filter(user -> user.getRole() == Roles.CUSTOMER || user.getRole() == null
      )
      .collect(Collectors.toList());
  }

  //Trả về danh sách nhân viên
  public List<Users> returnStaffs() {
    return usersRepo
      .findAll()
      .stream()
      .filter(user -> user.getRole() == Roles.STAFF)
      .collect(Collectors.toList());
  }

  //Thêm mới 1 nhân viên
  public ResponseEntity<?> addStaff(RegisterRequest request) {
    try {
      var cart = Cart.builder().cartProducts(null).build();
      var user = Users
        .builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Roles.STAFF)
        .cart(cart)
        .build();

      usersRepo.save(user);

      return ResponseEntity.ok("Thêm mới dữ liệu thành công!");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("Có lỗi xảy ra: " + e.getMessage());
    }
  }

  //Cập nhật thông tin nhân viên
  public ResponseEntity<?> updateStaff(int userID, UserRequest userRequest) {
    Optional<Users> userOptional = usersRepo.findById(userID);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();

      user.setName(userRequest.getName());
      user.setAddress(userRequest.getAddress());
      user.setBirth(userRequest.getBirth());
      user.setEmail(userRequest.getEmail());
      user.setGender(userRequest.getGender());
      user.setPhone(userRequest.getPhone());
      user.setAvatar(userRequest.getAvatar());

      usersRepo.save(user);

      return ResponseEntity.ok("Lưu nhân viên thành công!");
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy nhân viên!");
    }
  }

  //Xóa đi 1 nhân viên
  public ResponseEntity<?> removeStaff(int userID) {
    Optional<Users> userOptional = usersRepo.findById(userID);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();

      usersRepo.delete(user);

      return ResponseEntity.ok("Xóa nhân viên thành công!");
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy nhân viên!");
    }
  }
}
