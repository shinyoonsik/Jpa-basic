package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String zipcode;
    private String status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
