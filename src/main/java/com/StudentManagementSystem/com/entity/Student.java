package com.StudentManagementSystem.com.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id" , nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name="number")
    private Long number;

    @Column(name = "address")
    private String address;

    @Column(name="email")
    private String email;
}
