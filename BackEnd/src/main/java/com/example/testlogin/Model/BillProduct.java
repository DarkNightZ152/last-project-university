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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "bill_product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BillProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "billid")
  @JsonBackReference
  private Bills bill;

  @OneToOne
  @JoinColumn(name = "productid", referencedColumnName = "id")
  private Products product;

  @Column(name = "productname")
  private String productname;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private int price;

  @Column(name = "total")
  private int total;
}
