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

    // parent entity를 persist할 때, List안에 있는 모든 child를 persist하는 옵션; CascadeType.ALL
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
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
