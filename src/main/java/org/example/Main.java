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
            Member member = new Member();
            member.setName("member");
            member.setAddress(address);
            em.persist(member);

            // 임베디드 타입의 side-effect 해결책2
            // sol2 임베디트 타입은 레퍼런스 타입이므로 부작용을 원천 차단하기 위해 불변객체로 설계하자
            // 만약, 불변객체의 속성을 바꾸고 싶다면 객체를 새롭게 만들어서(new) 사용하자
            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
            Address newAddress2 = new Address("newCity", address.getStreet(), address.getZipcode());

            System.out.println("newAddress equals newAddress2: " + newAddress.equals(newAddress2));

            member.setAddress(newAddress);

            em.persist(member);

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