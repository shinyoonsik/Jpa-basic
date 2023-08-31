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
            member.setAge(20);
            member.setTeam(team);
            em.persist(member);

            // m.name이 null이면 '이름 없는 멤버'를 반환
            String query = "select coalesce(m.name, '이름 없는 멤버') as memberName from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for(String memberName : resultList){
                System.out.println("coalesce 결과: " + memberName);
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