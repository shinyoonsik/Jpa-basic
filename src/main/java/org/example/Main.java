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
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);


            Member member = new Member();
            member.setName("회원1");
            member.setAge(20);
            member.setTeam(teamA);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("회원2");
            member2.setAge(24);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("회원3");
            member3.setAge(40);
            member3.setTeam(teamB);
            em.persist(member3);

            // 영속 컨텍스트 초기화
            em.flush();
            em.clear();

//            String query = "select m from Member m";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for(Member mem : resultList){
//                // 지연로딩이면 mem.getTeam().getName()에서 getName()을 호출하는 시점에 Team을 조회하는 쿼리를 DB에 날림
//                // 회원1,2,3 => SQL
//                // 팀A => SQL 1번, 1차 캐시에서 한번
//                // 팀B => SQL 1번
//                // 만약 회원도 많고 팀도 다양하다면 SQL쿼리관련 IO작업이 많이 발생하여 성능하락
//                // 분명 멤버를 조회하는 쿼리를 하나(1)만 작성했을 뿐인데 내부적으로 JPA가 지연로딩을 통해 필요할 때마다 Team을 가져옴으로써
//                // 예상치 못한 N개의 쿼리가 발생 => N + 1문제
//                // (N + 1문제는 지연로딩뿐만 아니라 즉시로딩에서도 발생. 어느 시점에 N개의 쿼리가 발생하느냐의 차이만 있을뿐)
//                System.out.println("member = " + mem.getName() + ", " + mem.getTeam().getName());
//            }

            // SOL, fetch join으로 즉시로딩을 구현. 일반 join시 지연로딩이라면 사용할 때마다 join쿼리를 보내는 것이고
            // fetch join의 경우, 즉시로딩으로 join이 발생함(한 방에 다 가져옴)
            String solQuery = "select m from Member m join fetch m.team";
            List<Member> solResultList = em.createQuery(solQuery, Member.class).getResultList();
            for(Member mem : solResultList){
                System.out.println("member = " + mem.getName() + ", " + mem.getTeam().getName());
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