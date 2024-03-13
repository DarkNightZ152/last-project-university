package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.Model.ProductImages;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.ProductImagesRepo;
import com.example.testlogin.Repository.ProductsRepo;
import com.example.testlogin.Repository.UsersRepo;
import io.jsonwebtoken.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ProductImagesRepo productImagesRepo;
  private final UsersRepo usersRepo;
  private final ProductsRepo productsRepo;
  private final JwtService jwtService;

  public List<String> uploadImageToFileSystem(
    List<MultipartFile> files,
    String path
  ) throws IOException {
    List<String> filePaths = new ArrayList<>();
    try {
      for (MultipartFile file : files) {
        if (file != null) {
          String filePath = path + file.getOriginalFilename();
          file.transferTo(new File(filePath));
          filePaths.add(filePath);
        }
      }
      return filePaths;
    } catch (Exception e) {
      throw new IOException("Lỗi: " + e.getMessage());
    }
  }

  public ResponseEntity<?> addProductImages(
    int productID,
    List<MultipartFile> files
  ) {
    try {
      List<String> filePaths = uploadImageToFileSystem(
        files,
        "D:/Project/Nuxt/test-fe/public/productimages/"
      );
      for (String string : filePaths) {
        ProductImages image = new ProductImages();
        image.setImageurl(string.substring(30));
        image.setProduct(productsRepo.findById(productID).get());

        productImagesRepo.save(image);
      }
      return ResponseEntity.ok("Lưu ảnh sản phẩm thành công!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Lỗi xảy ra: " + e.getMessage());
    }
  }

  public ResponseEntity<?> addAvatar(String header, List<MultipartFile> files) {
    try {
      String token = header.substring(7);
      String username = jwtService.extractUsername(token);

      Optional<Users> userOptional = usersRepo.findByUsername(username);
      if (userOptional.isPresent()) {
        Users user = userOptional.get();

        if (!files.isEmpty()) {
          List<String> filePaths = uploadImageToFileSystem(
            files,
            "D:/Project/Nuxt/test-fe/public/avatar/"
          );
          for (String string : filePaths) {
            user.setAvatar(string.substring(30));
          }
          usersRepo.save(user);
          return ResponseEntity.ok("Lưu avatar thành công!");
        }

        return ResponseEntity.ok("Không có avatar nên sẽ không lưu!");
      } else {
        return ResponseEntity.badRequest().body("Không tìm thấy người dùng!");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Lỗi xảy ra: " + e.getMessage());
    }
  }
}
