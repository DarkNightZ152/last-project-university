package com.example.testlogin.Repository;

import com.example.testlogin.Model.News;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, Integer> {
  Optional<News> findBySeo(String seo);
}
