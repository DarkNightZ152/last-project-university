package com.example.testlogin.Service;

import com.example.testlogin.Model.Manufactory;
import com.example.testlogin.Repository.ManufactoryRepo;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManufactoryService {

  private final ManufactoryRepo manufactoryRepo;

  public List<Manufactory> GetManufactory() {
    return manufactoryRepo.findAll(Sort.by("ID").ascending());
  }

  public ResponseEntity<?> getManufactoryByID(int ID) {
    Optional<Manufactory> manufactoryOptional = manufactoryRepo.findById(ID);
    if (manufactoryOptional.isPresent()) {
      return ResponseEntity.ok(manufactoryOptional.get());
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy");
    }
  }

  public ResponseEntity<?> DeleteManufactory(Integer ID) {
    try {
      manufactoryRepo.deleteById(ID);
      return ResponseEntity.ok("Xóa thành công");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("Có lỗi xảy ra: " + e.getMessage());
    }
  }

  public ResponseEntity<?> AddAndUpdateManufactory(Manufactory manufactory) {
    manufactory.setSeo(generateSEOUrl(manufactory.getName()));
    manufactoryRepo.save(manufactory);
    return ResponseEntity.ok("Lưu hãng sản xuất thành công!");
  }

  /*Side function*/

  //Tạo SEO
  private String generateSEOUrl(String key) {
    String temp = Normalizer.normalize(key, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    temp = pattern.matcher(temp).replaceAll("");
    temp = temp.toLowerCase();
    temp = temp.replaceAll("[^a-z0-9 ]", "");
    temp = temp.replaceAll(" ", "-");
    return temp;
  }
}
