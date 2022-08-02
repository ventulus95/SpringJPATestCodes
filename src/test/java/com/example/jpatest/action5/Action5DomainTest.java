package com.example.jpatest.action5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class Action5DomainTest {

    @Autowired
    Action5DomainRepository repository;

    @Test
    @DisplayName("id가 있는 상태에서 id만 삭제하고 save하면, insert로 들어갈까?")
    @Rollback(value = false)
    void setIDInsert(){
        //given
        Action5Domain domain = Action5Domain.builder()
                .id(1L)
                .name("테스트")
                .build();

        repository.save(domain);

        //when
        Action5Domain findDomain = repository.findById(1L).orElseThrow();
        findDomain.setId(null); //애초에 안됨.
        repository.save(findDomain);

        //then
        List<Action5Domain> list = repository.findAll();
        assertThat(list.size(), is(2));
        assertThat(list.get(1).getId(), is(2L));
        assertThat(list.get(1).getName(), is("테스트"));
    }

    /**
     * https://vladmihalcea.com/clone-duplicate-entity-jpa-hibernate/
     * 지금의 경우는 연관관계가 아예 없어서 그냥 이름만 바꿔줬지만, 연관관계 생기면서부터는 연관관계와
     * 관련한 모든 설정들도 모두 새로 만들어줘야함.
     */
    @Test
    @DisplayName("clone 함수를 만들어서 해결")
    @Rollback(value = false)
    void cloneEntityInsert(){
        //given
        Action5Domain domain = Action5Domain.builder()
                .id(1L)
                .name("테스트")
                .build();

        repository.save(domain);

        //when
        Action5Domain findDomain = repository.findById(1L).orElseThrow();
        repository.save(findDomain.clone());

        //then
        List<Action5Domain> list = repository.findAll();
        assertThat(list.size(), is(2));
        assertThat(list.get(1).getId(), is(2L));
        assertThat(list.get(1).getName(), is("테스트의 사본"));
    }

}