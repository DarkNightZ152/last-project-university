package com.example.testlogin.Service;

import com.example.testlogin.DTO.Request.RegisterRequest;
import com.example.testlogin.DTO.Request.UserRequest;
import com.example.testlogin.Model.Cart;
import com.example.testlogin.Model.Roles;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.UsersRepo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffManageService {

  private final UsersRepo usersRepo;
  private final PasswordEncoder passwordEncoder;

  //Trả về danh sách nhân viên
  public List<Users> returnStaffs() {
    return usersRepo.findByRoleOrderByIDDesc("STAFF");
  }

  //Thêm mới 1 nhân viên
  public ResponseEntity<?> addNewStaff(RegisterRequest request) {
    var cart = Cart.builder().cartProducts(null).build();
    var user = Users
      .builder()
      .username(request.getUsername())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(Roles.CUSTOMER)
      .cart(cart)
      .build();

    usersRepo.save(user);

    return ResponseEntity.ok("Tạo nhân viên thành công");
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
