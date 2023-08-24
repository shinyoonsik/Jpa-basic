package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARENT")
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // CascadeType.ALL + orphanRemoval=true 모두 사용한 경우
    // child의 생명주기는 전적으로 parent 생명주기에 의존한다. 이는 자식엔티티에 대한 dao, repository가 없어도 됨을 의미한다.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    // 연관관계 편의 메소드
    public void addChild(Child child){
        this.childList.add(child);
        child.setParent(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
