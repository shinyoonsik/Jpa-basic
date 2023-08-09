package org.example.entity;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member; // 이 member는 read-only이다

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
