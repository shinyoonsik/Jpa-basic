package org.example;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name", unique = true, length = 20, columnDefinition = "varchar(100) default 'EMPTY'")
    private String name;

    @Enumerated(EnumType.STRING) // Java의 enum을 사용하고 싶은 경우, EnumType.STRING은 필수, ORDINAL을 사용하면 데이터가 꼬일수 있음
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Lob
    private String desc;

    @Transient
    private int temp; // 테이블에 맵핑된 컬럼이 아니라 메모리에만 두고 사용하고 싶을 때

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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
