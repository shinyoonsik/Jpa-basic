package org.example;

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

        try{
            Member insertedMember = insertMember(tx, em);
            selectMember(tx, em, insertedMember);
            updateMember(em, insertedMember);
//            deleteMember(em, insertedMember);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            // entityManager가 데이터베이스 커넥션을 가지고 동작하므로 사용후에 닫아주어야 한다.
            em.close();
        }
        emf.close();

        System.out.println("Hello world!");
    }

    private static Member insertMember(EntityTransaction tx, EntityManager em){
        Member member = new Member();
        member.setId(46L);
        member.setName("messi");

        em.persist(member);



        return member;
    }

    private static void selectMember(EntityTransaction tx, EntityManager em, Member member) {
        Member foundMember = em.find(Member.class, member.getId());
        System.out.println(foundMember);
    }


    private static void updateMember(EntityManager em, Member insertedMember) {
        insertedMember.setName("훌랄라");
    }


    private static void deleteMember(EntityManager em, Member insertedMember) {
        em.remove(insertedMember);
        System.out.println("member 삭제");
    }

}