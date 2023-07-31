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

            Member member = em.find(Member.class, 100L);
            member.setName("ZZZZZ");

//            em.persist(member); 업데이트를 위해 persist()를 호출할 필요없음.
            System.out.println(member);

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