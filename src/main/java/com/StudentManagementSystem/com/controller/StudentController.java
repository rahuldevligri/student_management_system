package com.StudentManagementSystem.com.controller;

import com.StudentManagementSystem.com.StudentManagementSystemApplication;
import com.StudentManagementSystem.com.entity.Student;
import com.StudentManagementSystem.com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student student1 = studentService.createStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable int id){
        Optional<Student> student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student studentDetails,@PathVariable int id){
        try {
            Student updatedStudent = studentService.updateStudent(studentDetails, id);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>("Student Deleted Successfully",HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}