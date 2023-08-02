package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IamYS");
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            System.out.println("---------------------");
            em.persist(member); // 이 시점에 insert SQL을 날림. tx.commit()이 아니라(IDENTITY전략을 가진 Entity가 예외적임)
            System.out.println("member의 ID: " + member.getId());
            System.out.println("---------------------");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();

        System.out.println("Hello world!");
    }


}