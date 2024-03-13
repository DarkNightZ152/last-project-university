package com.example.testlogin.Controller.Staff;

import com.example.testlogin.Model.News;
import com.example.testlogin.Service.ImageService;
import com.example.testlogin.Service.NewsService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class NewsController {

  private final NewsService newsService;
  private final ImageService imageService;

  @GetMapping("/news")
  public ResponseEntity<?> getAllNewss() {
    return newsService.getAllNews();
  }

  @GetMapping("/news/{id}")
  public ResponseEntity<?> getNewsByID(@PathVariable("id") int id) {
    return newsService.findNewsByID(id);
  }

  @PostMapping("/news/image/upload")
  public ResponseEntity<?> postMethodName(
    @RequestParam("image") List<MultipartFile> files,
    @RequestParam String path
  ) {
    return ResponseEntity.ok(imageService.uploadImageToFileSystem(files, path));
  }

  @PutMapping("/news/{id}")
  public ResponseEntity<?> updateNews(
    @PathVariable("id") int NewsID,
    @RequestBody News request
  ) {
    return newsService.updateNews(request);
  }

  @DeleteMapping("/news/{id}")
  public ResponseEntity<?> deleteNews(@PathVariable("id") int NewsID) {
    return newsService.removeNews(NewsID);
  }
}
