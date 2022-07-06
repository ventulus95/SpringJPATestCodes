package com.example.jpatest;

import com.example.jpatest.action1.Member;
import com.example.jpatest.action1.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
public class JpaLambdaTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("ifPresentOrElse를 True CASE")
    void ifPresentOrElse(){
        //given
        Member member = new Member();
        member.updateMember("A");
        em.persist(member);
        em.flush();

        //when
        memberRepository.findById(1L).ifPresentOrElse(
                member1 -> System.out.println(member1),
                () -> memberRepository.save(new Member("12", null))
        );


        //verify
        assertThat(memberRepository.findById(1L).get(), is(member));

    }

    @Test
    @DisplayName("ifPresentOrElse를 통해서 SELECT 되는지?")
    @Rollback(false)
    void ifPresentOrElseIsFalse(){
        //given
        Member member = new Member();
        member.updateMember("A");
        em.persist(member);
        em.flush();

        //when
        memberRepository.findById(2L).ifPresentOrElse(
                member1 -> System.out.println(member1),
                () -> {
                    memberRepository.save(new Member("12", null));
                    System.out.println("삽입됨?");
                }
        );


        //verify
        Member member1 = memberRepository.findById(2L).get();
        System.out.println(member1.getId()+" ~~~> "+member1.getName());
        assertThat(member1.getName(), is("12"));

    }
}
