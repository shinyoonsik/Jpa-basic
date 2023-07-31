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

        try{

            Member member1 = em.find(Member.class, 100L);
            Member member2 = em.find(Member.class, 100L);

            System.out.println(member1);
            System.out.println(member2);

            if(member1 == member2){
                System.out.println("is same!!!");
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();

        System.out.println("Hello world!");
    }


}