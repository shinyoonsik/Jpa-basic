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

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Q: Order만들고 Book만들고 관계 테이블 만든후 => add해주고 order와 book 각각을 persist해주면
            // CascadeType.ALL로 인해 orderItem도 생성된다
            em.flush();
            em.clear();

            Order order = new Order();
            order.setStatus("active");

            Book book = new Book();
            book.setName("지식 경영법");
            book.setIsbn("12321312");
            book.setAuthor("정약용");

            OrderItem orderItem = new OrderItem();
            orderItem.setCount(1);

            order.addOrderItem(orderItem);
            book.addOrderItem(orderItem);

            System.out.println("before order persist========");
            System.out.println(order.getId());
            System.out.println(orderItem.getId());
            em.persist(order);
            System.out.println("after order persist========");
            System.out.println(order.getId());
            System.out.println(orderItem.getId());

            em.persist(book);

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