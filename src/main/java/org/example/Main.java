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

            // 영속 컨텍스트 초기화
            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember =====================");
            System.out.println("refMember: " + refMember.getClass());
            System.out.println(refMember.getId());
            System.out.println(refMember.getName());  // 실제로 데이터를 사용할 때, DB를 조회한다

            // member를 가지고 오면서 team을 조인해서 가져온다
//            Member foundMember = em.find(Member.class, member.getId());
//            System.out.println("foundMember ===================");
//            System.out.println(foundMember.getId());
//            System.out.println(foundMember.getName());

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