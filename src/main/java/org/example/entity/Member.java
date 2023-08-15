package org.example.entity;


import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}
