package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            insertMember(tx, em);

        }catch (Exception e){
            tx.rollback();
        }finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();

        System.out.println("Hello world!");
    }

    private static void insertMember(EntityTransaction tx, EntityManager em){
        Member member = new Member();
        member.setId(3L);
        member.setName("leo");

        em.persist(member);

        tx.commit();
    }
}