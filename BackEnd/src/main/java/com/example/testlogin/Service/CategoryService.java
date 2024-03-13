package com.example.testlogin.Service;

import com.example.testlogin.Model.Category;
import com.example.testlogin.Repository.CategoryRepo;
import com.example.testlogin.Repository.ProductsRepo;
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
public class CategoryService {

  private final CategoryRepo categoryRepo;
  private final ProductsRepo productsRepo;

  public List<Category> GetCategory() {
    return categoryRepo.findAll(Sort.by("ID").ascending());
  }

  public ResponseEntity<?> getCategoryByID(int ID) {
    Optional<Category> categoryOptional = categoryRepo.findById(ID);
    if (categoryOptional.isPresent()) {
      return ResponseEntity.ok(categoryOptional.get());
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy dữ liệu!");
    }
  }

  public ResponseEntity<?> DeleteCategory(Integer ID) {
    try {
      categoryRepo.deleteById(ID);
      return ResponseEntity.ok("Xóa thành công");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("Có lỗi xảy ra: " + e.getMessage());
    }
  }

  public ResponseEntity<?> AddAndUpdateCategory(Category category) {
    try {
      category.setSeo(generateSEOUrl(category.getName()));
      categoryRepo.save(category);
      return ResponseEntity.ok("Lưu dữ liệu thành công!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Đã có lỗi: " + e.getMessage());
    }
  }

  public ResponseEntity<?> returnProductsByCategory(String seo) {
    return ResponseEntity.ok(productsRepo.findByCategory_seo(seo));
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
