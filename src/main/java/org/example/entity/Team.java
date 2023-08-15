package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 비즈니스 로직상 Team입장에서 Member들을 불러와 사용하는 경우가 많다면 양방향 관계 추가
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
