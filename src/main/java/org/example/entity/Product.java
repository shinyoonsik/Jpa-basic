package org.example.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product") // 필요시, 양방향 연관관계 사용
    private List<MemberProduct> memberProducts = new ArrayList<>();

}
