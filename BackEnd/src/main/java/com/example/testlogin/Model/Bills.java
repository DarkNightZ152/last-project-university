package com.example.testlogin.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

@Table(name = "bills")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bills {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  @JsonBackReference
  private Users user;

  @Column(name = "customer_name")
  private String customer_name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "address")
  private String address;

  @Column(name = "note")
  private String note;

  @OneToMany(mappedBy = "bill", orphanRemoval = true)
  @JsonManagedReference
  private List<BillProduct> billProduct;

  @Column(name = "total")
  private int total;

  @Column(name = "discount")
  private int discount;

  @Column(name = "totalafterdiscount")
  private int totalAfterDiscount;

  @Column(name = "created_on")
  @CurrentTimestamp
  private Date created_on;

  @Column(name = "end_on")
  private Date end_on;

  @Column(name = "reason")
  private String reason;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "statusid")
  @JsonManagedReference
  private Status status;

  @Builder.Default
  @Column(name = "is_calculated")
  private boolean isCalculated = false;

  public boolean getIsCalculated() {
    return isCalculated;
  }

  public void setIsCalculated(boolean isCalculated) {
    this.isCalculated = isCalculated;
  }
}
