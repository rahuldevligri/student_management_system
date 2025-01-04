package com.StudentManagementSystem.com.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int laptopId;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "brand")
    private String brand;

    // Creates the foreign key column `student_id` in the `laptops` table.
    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
}
