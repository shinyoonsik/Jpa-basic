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

            System.out.println("================before flush");

            // 벌크 연산
            // JPQL이 실행되면 em.flush()자동 호출 => 위의 member들에 대한 insert가 실행되고 update쿼리 실행됨
            int resultCount = em.createQuery("update Member m set m.age = 100")
                            .executeUpdate();

            System.out.println("================after flush");
            System.out.println(resultCount);

            Member member1 = em.find(Member.class, member.getId()); // 영속 컨텍스트에 이미 있는 member를 가지고 오기 때문에 member.getId()는 20을 호출한다
            System.out.println(member1.getAge());

            // sol => 영속 컨텍스트 초기화
            em.clear();
            System.out.println("=============영속 컨텍스트 초기화");
            Member foundMember = em.find(Member.class, member.getId());
            System.out.println(foundMember.getAge());

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