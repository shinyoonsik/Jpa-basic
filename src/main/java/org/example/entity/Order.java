package org.example.entity;

import org.example.enumClass.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    // 단방향 설계가 중요하다. 양방향은 개발하다 필요할 때 코드를 추가해주면 된다.
    // 무엇보다 중요한건 단방향 설계이다
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 양방향 연관관계 맵핑(필요시)
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    // 연관관계 편의 메소드
    public void addOrderProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
