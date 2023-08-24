package org.example;


import org.example.entity.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

            em.flush();
            em.clear();

//            System.out.println("------");
//            Member foundMember = em.find(Member.class, member.getId()); // 즉시 로딩 -> member와 team을 조인해서 한 번에 가져옴
//            System.out.println("foundMember = " + foundMember.getTeam().getClass());
//            System.out.println("=======");
//            System.out.println("foundMember의 Team: " + fo undMember.getTeam().getName());

            /**
             * 즉시 로딩으로 설정하면 JPQL에서 N + 1문제를 야기한다
             * JPQL은 SQL로 번역이 된다. 그런데 Member의 team이 즉시로딩이면 가져온 member만큼 team을 다시 조회해서 가져온다
             * 즉, Member조회를 위한 쿼리(JPQL)가 1이고 이에 부수적으로 N개의 쿼리가 딸려 나갈 수 있다. => N + 1문제
             */
            List<Member> resultList = em.createQuery("select m from Member  m", Member.class).getResultList();


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