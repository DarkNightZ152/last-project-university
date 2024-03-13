package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.DTO.Request.UserRequest;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.UsersRepo;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepo usersRepo;
  private final JwtService jwtService;

  public ResponseEntity<?> userGetInfor(String token) {
    String username = jwtService.extractUsername(token);
    Users user = usersRepo
      .findByUsername(username)
      .orElseThrow(() -> new NoSuchElementException("User not found"));

    return ResponseEntity.ok(user);
  }

  //Đổi mật khẩu
  public ResponseEntity<?> changePassword(
    String header,
    String oldpassword,
    String newpassword
  ) {
    String token = header.substring(7);
    String username = jwtService.extractUsername(token);

    Optional<Users> userOptional = usersRepo.findByUsername(username);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();

      // So sánh mật khẩu cũ
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      if (passwordEncoder.matches(oldpassword, user.getPassword())) {
        // Mật khẩu cũ đúng, cập nhật mật khẩu mới
        String encodedNewPassword = passwordEncoder.encode(newpassword);
        user.setPassword(encodedNewPassword);

        // Lưu thay đổi vào cơ sở dữ liệu
        usersRepo.save(user);

        // Trả về thông báo thành công hoặc ResponseEntity phù hợp
        return ResponseEntity.ok("Mật khẩu đã được thay đổi");
      } else {
        // Mật khẩu cũ không đúng, trả về thông báo lỗi hoặc ResponseEntity phù hợp
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Mật khẩu cũ không chính xác");
      }
    } else {
      // Không tìm thấy tài khoản, trả về thông báo lỗi hoặc ResponseEntity phù hợp
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Không tìm thấy tài khoản");
    }
  }

  //Thay đổi thông tin người dùng
  public ResponseEntity<?> updateUserInfo(
    String header,
    UserRequest userRequest
  ) {
    String token = header.substring(7);
    String username = jwtService.extractUsername(token);

    Optional<Users> userOptional = usersRepo.findByUsername(username);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();

      user.setName(userRequest.getName());
      user.setAddress(userRequest.getAddress());
      user.setBirth(userRequest.getBirth());
      user.setEmail(userRequest.getEmail());
      user.setGender(userRequest.getGender());
      user.setPhone(userRequest.getPhone());
      user.setAvatar(userRequest.getAvatar());

      // Lưu thay đổi vào cơ sở dữ liệu
      usersRepo.save(user);

      // Trả về thông báo thành công hoặc ResponseEntity phù hợp
      return ResponseEntity.ok(userOptional.get());
    } else {
      // Không tìm thấy thông tin người dùng, trả về thông báo lỗi hoặc ResponseEntity phù hợp
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Không tìm thấy thông tin người dùng");
    }
  }
}
