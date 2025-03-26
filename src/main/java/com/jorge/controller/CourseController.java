package com.jorge.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jorge.model.Course;
import com.jorge.repository.CourseRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CourseController {

  private final CourseRepository courseRepository;

  @GetMapping
  @CrossOrigin(origins = "*")
  @Cacheable(value = "courses", key = "'all'")
  public ResponseEntity<List<Course>> list() {
    List<Course> courses = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    return ResponseEntity.ok(courses);
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "*")
  @Cacheable(value = "courses", key = "#id")
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @CrossOrigin(origins = "*")
  @CacheEvict(value = "courses", allEntries = true)
  public ResponseEntity<?> create(@RequestBody Course course) {
    if (course.getTitle() == null || course.getTitle().isEmpty()) {
      return ResponseEntity.badRequest().body("Title cannot be null or empty");
    }
    if (course.getPrice() == null) {
      return ResponseEntity.badRequest().body("Price cannot be null");
    }
    Course savedCourse = courseRepository.save(course);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
  }

  @PostMapping("/batch")
  @CrossOrigin(origins = "*")
  @CacheEvict(value = "courses", allEntries = true)
  public ResponseEntity<?> createBatch(@RequestBody List<Course> courses) {
    if (courses == null || courses.isEmpty()) {
      return ResponseEntity.badRequest().body("Courses list cannot be null or empty");
    }
    
    for (Course course : courses) {
      if (course.getTitle() == null || course.getTitle().isEmpty()) {
        return ResponseEntity.badRequest().body("Title cannot be null or empty");
      }
      if (course.getPrice() == null) {
        return ResponseEntity.badRequest().body("Price cannot be null");
      }
    }
    
    List<Course> savedCourses = courseRepository.saveAll(courses);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCourses);
  }

  @PutMapping("/{id}")
  @CrossOrigin(origins = "*")
  @Transactional
  @CacheEvict(value = "courses", allEntries = true)
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Course course) {
    if (course.getTitle() == null || course.getTitle().isEmpty()) {
      return ResponseEntity.badRequest().body("Title cannot be null or empty");
    }
    if (course.getPrice() == null) {
      return ResponseEntity.badRequest().body("Price cannot be null");
    }
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setTitle(course.getTitle());
          recordFound.setSubtitle(course.getSubtitle());
          recordFound.setLocal(course.getLocal());
          recordFound.setImage(course.getImage());
          recordFound.setPrice(course.getPrice());
          recordFound.setUrgency(course.getUrgency());
          recordFound.setLink(course.getLink());
          Course updated = courseRepository.save(recordFound);
          return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "*")
  @CacheEvict(value = "courses", allEntries = true)
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          courseRepository.deleteById(id);
          return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
  }
}