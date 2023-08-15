package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderPrice;
    private int count;
}
