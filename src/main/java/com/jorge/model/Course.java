package com.jorge.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import lombok.Data;

@Data
@Entity
@Table(name = "courses", indexes = {
    @Index(name = "idx_course_title", columnList = "title"),
    @Index(name = "idx_course_urgency", columnList = "urgency"),
    @Index(name = "idx_course_price", columnList = "price")
})
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