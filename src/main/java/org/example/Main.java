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

            List<Member> resultList = em.createQuery("select m from Member m ", Member.class).getResultList();
            Member member1 = resultList.get(0);
            member1.setName("sssss");

            // JPQL은 단순한 select쿼리지만, 실제로 변환된 sql은 member와 team을 join하는 쿼리가 날라감
            // But, 관리의 편의상, JPQL도 join으로 바꾸자
            List<Team> resultList2 = em.createQuery("select m.team from Member m", Team.class).getResultList();
            Team team1 = resultList2.get(0);
            team1.setName("Mancherster");

            // 이렇게 JPQL도 join으로 사용해야 관리가 쉽다
            List<Team> resultList3 = em.createQuery("select m.team from Member m join m.team", Team.class).getResultList();

            // 임베디드 타입 프로젝션
            List<Address> resultList4 = em.createQuery("select o.address from Order o", Address.class).getResultList();

            // 스칼라 타입 프로젝션. 조회하는 name과 age의 type이 달라 타입을 명시하지 않는다
            List<Object[]> resultList5 = em.createQuery("select distinct m.name, m.age from Member m").getResultList();
            for(Object[] o : resultList5) {
                System.out.println("Query result: " + o[0] + " " + o[1]);
            }

            // new 명령어로 조회(DTO로 결과를 묶어서 가져오기 => 보기 편함)
            List<MemberDTO> resultList1 = em.createQuery("select new org.example.MemberDTO(m.name, m.age) from Member m", MemberDTO.class).getResultList();
            for(MemberDTO memberDTO : resultList1){
                System.out.println("new result: " + memberDTO.getName() + " " + memberDTO.getAge());
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