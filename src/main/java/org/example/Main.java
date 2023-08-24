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
            Team team = new Team();
            team.setName("리버풀");
            em.persist(team);

            Member member = new Member();
            member.setName("yoonsik");
            member.setTeam(team);
            em.persist(member);
다
            em.flush();
            em.clear();

            System.out.println("------");
            Member foundMember = em.find(Member.class, member.getId());
            System.out.println("foundMember = " + foundMember.getTeam().getClass());
            System.out.println("=======");
            System.out.println("foundMember의 Team: " + foundMember.getTeam().getName()); // 사용할 때, Team을 조회한


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();
    }
}