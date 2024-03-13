package com.example.testlogin.Service;

import com.example.testlogin.Model.News;
import com.example.testlogin.Repository.NewsRepo;
import java.text.Normalizer;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

  private final NewsRepo newsRepo;

  public ResponseEntity<?> getAllNews() {
    return ResponseEntity.ok(newsRepo.findAll());
  }

  public ResponseEntity<?> findNewsByID(int ID) {
    Optional<News> newsOptional = newsRepo.findById(ID);
    if (newsOptional.isPresent()) {
      return ResponseEntity.ok(newsOptional.get());
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy dữ liệu");
    }
  }

  public ResponseEntity<?> findNewsBySeo(String seo) {
    Optional<News> newsOptional = newsRepo.findBySeo(seo);
    if (newsOptional.isPresent()) {
      return ResponseEntity.ok(newsOptional.get());
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy dữ liệu");
    }
  }

  public ResponseEntity<?> addNews(News requestNews) {
    var news = News
      .builder()
      .title(requestNews.getTitle())
      .content(requestNews.getContent())
      .seo(generateSEOUrl(requestNews.getTitle()))
      .build();
    newsRepo.save(news);
    return ResponseEntity.ok("Thêm mới tin tức thành công");
  }

  public ResponseEntity<?> updateNews(News requestNews) {
    var news = News
      .builder()
      .title(requestNews.getTitle())
      .content(requestNews.getContent())
      .seo(generateSEOUrl(requestNews.getTitle()))
      .build();
    newsRepo.save(news);
    return ResponseEntity.ok("Thêm mới tin tức thành công");
  }

  public ResponseEntity<?> removeNews(int newsID) {
    News news = newsRepo.findById(newsID).get();
    newsRepo.delete(news);
    return ResponseEntity.ok("Xóa thành công");
  }

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
