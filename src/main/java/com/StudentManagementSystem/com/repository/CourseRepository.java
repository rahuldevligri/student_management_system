package com.StudentManagementSystem.com.repository;

import com.StudentManagementSystem.com.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
