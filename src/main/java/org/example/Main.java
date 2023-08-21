package org.example;


import org.example.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IamYS");
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("훌랄라");
            member.setCreatedBy("ys");
            member.setCreatedAt(LocalDateTime.now());
            member.setUpdatedBy("ys");
            member.setUpdatedAt(LocalDateTime.now());
            System.out.println("=======before Member");
            em.persist(member);
            System.out.println("=======after Member");

            Order order = new Order();
            order.setMember(member);
            order.setStatus("active");
            em.persist(order);

            Delivery delivery = new Delivery();
            delivery.setStatus("active");
            delivery.setAddress("서울시 관악구");
            em.persist(delivery);

            order.setDelivery(delivery);

            // 단일 테이블 전략
            Book book = new Book();
            book.setName("지식 경영법");
            book.setIsbn("12321312");
            book.setAuthor("정약용");
            em.persist(book);


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(book);
            orderItem.setCount(1);
            em.persist(orderItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();

        System.out.println("Good Luck!");
    }


}