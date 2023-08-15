package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "LOCKER")
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 일대일 양방향, member는 읽기전용으로 사용
    @OneToOne(mappedBy = "locker")
    private Member member;
}
