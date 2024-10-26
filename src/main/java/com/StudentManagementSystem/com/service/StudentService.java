package com.StudentManagementSystem.com.service;

import com.StudentManagementSystem.com.entity.Student;
import com.StudentManagementSystem.com.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Student studentDetails, int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDetails.getName());
            student.setNumber(studentDetails.getNumber());
            student.setEmail(studentDetails.getEmail());
            student.setAddress(studentDetails.getAddress());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    public void deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }
}
