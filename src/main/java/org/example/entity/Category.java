package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Category밑에 여러 개의 Category가 있는 경우에 대한 모델링
    // 1 : M의 재귀적 관계를 나타냄 => parent, child
    // parent == null이면 최상위 카테고리를 의미함
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // 양방향 설정
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
