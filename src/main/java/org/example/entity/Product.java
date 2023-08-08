package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
