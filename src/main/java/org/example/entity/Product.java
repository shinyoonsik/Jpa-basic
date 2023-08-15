package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // read-only, 양방향 관계
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();
}
