package com.example.jpatest.action4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class action4Test {

    private final static Logger log = LoggerFactory.getLogger(action4Test.class);

    @Autowired
    TestDomainRepository testDomainRepository;

    @Test
    @DisplayName("일단 테스트 도메인이 잘 작동하는지?")
    void normalTest(){
        //given
        TestDomain domain = TestDomain.builder()
                .id(1L)
                .id2(2L)
                .id3(3L)
                .id4(4L)
                .build();

        testDomainRepository.save(domain);


        //when
        TestDomainPk pk = TestDomainPk.builder()
                .id(1L).id2(2L).id3(3L).id4(4L)
                .build();
        TestDomain testDomain = testDomainRepository.findById(pk).get();

        //then
        assertThat(testDomain.getId(), is(domain.getId()));
    }

    @Test
    @DisplayName("이미 한번 값이 들어간 상태에서 다음에 동일한 엔티티가 들어간 상황")
    @Sql("classpath:test.sql")
    @Rollback(value = false)
    void duplicateNotFindInsert(){
        log.info(String.valueOf(testDomainRepository.findAll().size()));
        //given

        //when
        TestDomain domain1 = TestDomain.builder()
                .id(1L)
                .id2(2L)
                .id3(3L)
                .id4(4L)
                .build();
        testDomainRepository.save(domain1);

        //then
        TestDomainPk pk = TestDomainPk.builder()
                .id(1L).id2(2L).id3(3L).id4(4L)
                .build();
        List<TestDomain> list = testDomainRepository.findAllById(List.of(pk));
        List<TestDomain> list2 = testDomainRepository.findAll();

        assertThat(list.size(), is(1));
        assertThat(list2.size(), is(1));
    }
}
