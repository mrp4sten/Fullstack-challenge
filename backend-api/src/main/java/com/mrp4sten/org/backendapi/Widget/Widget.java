package com.mrp4sten.org.backendapi.Widget;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "widgets")
public class Widget {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
  @Column(unique = true)
  private String name;

  @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
  private String description;

  @DecimalMin(value = "1.00", inclusive = true, message = "Price must be greater than or equal to 1")
  @DecimalMax(value = "20000.00", inclusive = true, message = "Price must be less than or equal to 20000")
  private BigDecimal price;
}