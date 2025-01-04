package com.StudentManagementSystem.com.controller;

import com.StudentManagementSystem.com.entities.Student;
import com.StudentManagementSystem.com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student student1 = studentService.createStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> students) {
        List<Student> savedStudents = new ArrayList<>();
        for (Student student : students) {
            savedStudents.add(studentService.createStudent(student));
        }
        return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/studentId/")
    public ResponseEntity<Student> getStudentById(@RequestParam long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/id/")
    public ResponseEntity<Optional<Student>> getStdById(@RequestParam long id){
        Optional<Student> student = studentService.getStdById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/name/")
    public ResponseEntity<Student> getStudentByName(@RequestParam("name") final String name){
        Student student = studentService.getStudentByName(name);
        try {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student studentDetails,@PathVariable long id){
        try {
            Student updatedStudent = studentService.updateStudent(studentDetails, id);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>("Student Deleted Successfully",HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Student of ID: "+id+" isn't found...",HttpStatus.NOT_FOUND);
        }
    }
}