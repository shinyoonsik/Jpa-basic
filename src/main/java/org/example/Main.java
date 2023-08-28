package org.example;


import org.example.entity.*;
import org.example.type.Address;
import org.example.type.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IamYS");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("city", "street", "10000");
            Member member1 = new Member();
            member1.setName("member1");
            member1.setAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");

            // 임베디드타입을 공유해서 쓰면 레퍼런스를 참조하므로 의도치않게 member2의 address도 변경된다 => call by ref
            // 따라서, 의도하지 않았면 값을 복사해서 사용하자
            member1.getAddress().setCity("hello!");

            // sol, member2는 복사한 copyAddress를 사용하자
            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            member2.setAddress(copyAddress);

            em.persist(member2);

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