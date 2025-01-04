package com.StudentManagementSystem.com.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    private String pId;
    private String productName;
    boolean active;
    boolean price;
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();
}
