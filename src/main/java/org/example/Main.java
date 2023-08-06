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
            System.out.println("------before insert team");
            em.persist(team);
            System.out.println("------after insert team");

            Member member = new Member();
            member.setUsername("member2");
            member.setTeamId(team.getId());

            System.out.println("-----before insert member");
            em.persist(member);
            System.out.println("-----after insert member");

            // member의 team을 가져와 처리하는 로직이 있다면
            // 아래와 같이 id를 가져와서 find해야하는 번잡함이 있다. 객체지향스럽지 않아!
            // member.getTeam()이 객체지향 스러움
            Long teamId = member.getTeamId();
            Team foundTeam = em.find(Team.class, teamId);
            System.out.println("member의 team: " + foundTeam.getName());


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