package com.example.testlogin.Controller.Staff;

import com.example.testlogin.Model.Category;
import com.example.testlogin.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/category")
  public ResponseEntity<?> getAllManufactories() {
    return ResponseEntity.ok(categoryService.GetCategory());
  }

  @PostMapping("/category/add")
  public ResponseEntity<?> postcategory(@RequestBody Category category) {
    return categoryService.AddAndUpdateCategory(category);
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<?> getcategory(@PathVariable("id") int ID) {
    return categoryService.getCategoryByID(ID);
  }

  @PutMapping("/category/update")
  public ResponseEntity<?> updatecategory(@RequestBody Category category) {
    return categoryService.AddAndUpdateCategory(category);
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable("id") int ID) {
    return categoryService.DeleteCategory(ID);
  }
}
