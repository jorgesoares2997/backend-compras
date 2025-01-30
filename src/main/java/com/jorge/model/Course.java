package com.jorge.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 200, nullable = false)
  private String title;

  @Column(length = 200)
  private String subtitle;

  @Column(length = 200)
  private String local;

  @Column(length = 200)
  private String image;

  @Column(length = 200)
  private BigDecimal price;

  @Column(length = 200)
  private String urgency;

  @Column(length = 200)
  private String link;
}
