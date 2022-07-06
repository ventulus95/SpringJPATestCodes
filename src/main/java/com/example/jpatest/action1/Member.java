package com.example.jpatest.action1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public void updateTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    public void updateMember(String name){
        this.name = name;
    }

}
