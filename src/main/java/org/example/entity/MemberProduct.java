package org.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_PRODUCT")
public class MemberProduct {

    // Trade-Off이다...But, 유연한 구조를 위해 비즈니스적으로 의미가 없느 id(인조키)를 만들어서 사용하자
    // 필요한 경우, 제약조건을 특정 컬럼에 추가하자
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long orderMount;
    private LocalDateTime orderDate;


}
