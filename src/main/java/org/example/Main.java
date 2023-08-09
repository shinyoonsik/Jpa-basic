package org.example;

import org.example.entity.Locker;
import org.example.entity.Member;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IamYS");
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Locker locker = new Locker();
            locker.setName("myLocker");
            em.persist(locker);

            Member member = new Member();
            member.setName("ys");
            member.setLocker(locker);
            em.persist(member);

            Member lockerMember = locker.getMember();
            System.out.println("my name is " + lockerMember.getName());


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