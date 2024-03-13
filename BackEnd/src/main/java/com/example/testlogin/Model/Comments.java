package com.example.testlogin.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Comments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  @JsonBackReference
  private Users users;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productid")
  @JsonBackReference
  private Products product;

  @Column(name = "comment")
  private String comment;
}
