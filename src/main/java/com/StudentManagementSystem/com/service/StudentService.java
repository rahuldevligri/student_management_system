package com.StudentManagementSystem.com.service;

import com.StudentManagementSystem.com.entities.Student;
import com.StudentManagementSystem.com.repository.AddressRepository;
import com.StudentManagementSystem.com.repository.LaptopRepository;
import com.StudentManagementSystem.com.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Student createStudent(Student student) {
        if (student.getLaptop() != null) {
            student.getLaptop().setStudent(student);
        }
        return studentRepository.save(student);
    }

    public void createStudents(List<Student> students) {
        // Here, you can add logic to save students in the database.
        // For now, we'll just print the student details to simulate processing.
        students.forEach(student -> {
            System.out.println("Saving student: " + student.getName());
        });
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepository.findStudentById(id);
    }

    public Optional<Student> getStdById(long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Student studentDetails, long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDetails.getName());
            student.setNumber(studentDetails.getNumber());
            student.setEmail(studentDetails.getEmail());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    public void deleteStudent(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    public Student getStudentByName(String name) {
        return studentRepository.findByStudentName(name);
    }


}
