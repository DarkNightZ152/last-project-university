package com.example.testlogin.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "created")
  private Date created;

  @Column(name = "seo")
  private String seo;
}
