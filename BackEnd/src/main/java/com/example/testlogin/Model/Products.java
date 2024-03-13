package com.example.testlogin.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

@Table(name = "products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "categoryid", referencedColumnName = "ID")
  @JsonManagedReference
  private Category category;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "manufactoryid", referencedColumnName = "ID")
  @JsonManagedReference
  private Manufactory manufactory;

  @Column(name = "description")
  private String description;

  @Column(name = "detail_des")
  private String detail_des;

  @Column(name = "amount")
  private int amount;

  @Column(name = "quality")
  private String quality;

  @Column(name = "price")
  private int price;

  @Column(name = "discount")
  private int discount;

  @Column(name = "guarantee")
  private int guarantee;

  @Column(name = "buyed")
  private int buyed;

  @Column(name = "viewed")
  private int viewed;

  @Column(name = "rated_total")
  private int rated_total;

  @Column(name = "rated_count")
  private double rated_count;

  @Column(name = "created")
  @CurrentTimestamp
  private Timestamp created;

  @OneToMany(mappedBy = "product", orphanRemoval = true)
  @JsonManagedReference
  @OrderBy("id DESC")
  private List<ProductImages> productImages;

  @OneToMany(mappedBy = "product", orphanRemoval = true)
  @JsonManagedReference
  private List<Comments> comments;

  @Column(name = "seo")
  private String seo;
}
