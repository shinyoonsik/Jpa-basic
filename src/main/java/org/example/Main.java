package org.example;


import org.example.entity.Item;
import org.example.entity.Movie;

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
            Movie movie = new Movie();
            movie.setActor("준호");
            movie.setDirector("감독");
            movie.setName("제목은 괴물");
            movie.setPrice(10000);

            em.persist(movie);

            Movie movie2 = new Movie();
            movie2.setActor("호호");
            movie2.setDirector("감독2");
            movie2.setName("제목은 괴물2");
            movie2.setPrice(10000);

            em.persist(movie2);

            em.flush();
            em.clear();

            em.find(Item.class, movie.getId());

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