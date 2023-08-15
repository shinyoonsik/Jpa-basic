package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_PRODUCT")
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
