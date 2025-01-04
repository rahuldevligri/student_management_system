package com.StudentManagementSystem.com;

import com.StudentManagementSystem.com.controller.StudentController;
import com.StudentManagementSystem.com.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@ActiveProfiles("test")
//@ContextConfiguration(classes = {Student.class, StudentRepository.class, StudentController.class, StudentService.class, JDBCConfig.class, H2TestConfig.class})
class StudentManagementSystemApplicationTests {

    @Autowired
    StudentController studentController;

    @Test
    public void test() {
        assertEquals("TEST", "TEST");
    }

    @Test
    public void testGetStudent() throws Exception{
        ResponseEntity<Optional<Student>> response = studentController.getStdById(1) ;
        Optional<Student> student = response.getBody();
        assertEquals("John Doe", student.get().getName());
        assertEquals("1234567890", student.get().getNumber());
//        assertEquals("123 Elm Street, Springfield", student.get().getAddress());
        assertEquals("john.doe@example.com", student.get().getEmail());
    }
}
