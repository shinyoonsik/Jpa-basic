package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    // read-only, 양방향 설정
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
