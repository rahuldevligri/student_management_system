package com.StudentManagementSystem.com.repository;

import com.StudentManagementSystem.com.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM student where name = :name", nativeQuery = true)
    Student findByStudentName(@Param("name")String name);

    @Query(value = "SELECT * FROM student where student_id = :id ", nativeQuery = true)
    Student findStudentById(@Param("id") long id);
}
