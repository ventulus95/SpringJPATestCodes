package com.example.jpatest;

import com.example.jpatest.action1.Member;
import com.example.jpatest.action1.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class JpaTestApplicationTests {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("연관관계 persist 오류 찾는 문제.")
    void test1234(){
        //given
        Team team1 = new Team("t1");
        Team team2 = new Team("t2");
        em.persist(team1);
        em.persist(team2);

        Member m1 = new Member("A", team1);
        Member m2 = new Member("B", team2);
        Member m3 = new Member("C", team2);

        em.persist(m1);
        em.persist(m2);
        em.persist(m3);

        em.flush();

        //when
        m1.updateTeam(team2);
        em.persist(m1);

        m2.updateMember("DD");
        em.persist(m2);

        m3.updateMember("C");
        em.persist(m3);

        em.flush();
        //then
        assertThat(m1.getTeam(), is(team2));
        assertThat(m2.getName(), is("DD"));
        assertThat(m3.getName(), is("C"));
    }


}
