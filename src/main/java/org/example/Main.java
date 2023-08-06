package org.example;

import org.example.entity.Member;
import org.example.entity.Team;

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
            Team team = new Team();
            team.setName("TeamB");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member2");
            member.setTeam(team);
            em.persist(member);

            // 1차 캐시에 저장된 값을 가져오는 것이 아니라 DB에서 직접 가져오는 쿼리를 확인하고 싶다면
            em.flush();
            em.clear();

            // team_id를 가지고 다시 em.find()할 필요가 없다
            Member member1 = em.find(Member.class, member.getId());
            System.out.println(member1.getUsername());
            System.out.println(member1.getTeam().getName());

            // member.getTeamId()를 해서 teamId로 team을 다시 조회할 필요가 없다.
            Team team1 = member1.getTeam();
            System.out.println(team1.getName());

            System.out.println(member1.getTeam() == team1);

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