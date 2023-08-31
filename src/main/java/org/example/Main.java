package org.example;


import org.example.entity.*;
import org.example.type.Address;
import org.example.type.Period;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IamYS");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("Liverpool");
            em.persist(team);

            Member member = new Member();
            member.setAge(10);
            member.setName("ys");
            member.setTeam(team);
            em.persist(member);

            String jpql = "select m from Member m order by m.id asc";
            List<Member> resultList = em.createQuery(jpql, Member.class)
                    .setFirstResult(0) // 조회 시작 위치(0부터 시작)
                    .setMaxResults(5) // 조회할 데이터 수
                    .getResultList();
            for (Member foundMember : resultList) {
                System.out.println("member: " + foundMember.getId() + " " + foundMember.getName());
            }


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