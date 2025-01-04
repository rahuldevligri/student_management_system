package com.StudentManagementSystem.com.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name="number")
    private String number;

    @Column(name="email")
    private String email;

    // The ownership of the relationship is in the `Laptop` entity.
    // CascadeType propagates operations to Laptop
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)

    private Laptop laptop;

    //many address
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
     // Manage the forward relationship serialization
    private List<Address> addressList =  new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore // Serialize course details
    private List<Course> courses = new ArrayList<>();

}
