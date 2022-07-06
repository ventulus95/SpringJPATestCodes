package com.example.jpatest.action3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class action3Test {

    private final static Logger log = LoggerFactory.getLogger(action3Test.class);

    @Autowired
    LocalEntityRepository localEntityRepository;

    @Test
    @DisplayName("long type처리 가능한지?")
    void typeReturnValue(){
        //given
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        LocalEntity entity = LocalEntity.builder().end_date(now).start_date(now).build();
        localEntityRepository.save(entity);

        //when
        Long entity1 = localEntityRepository.query1(entity.getEid());
//        List<LocalEntity> entity1 = localEntityRepository.query1();

        //then
        assertThat(entity1, is(entity.getStart_date().atZone(ZoneId.systemDefault()).toEpochSecond()));

        log.info("HEELLLLOOOOOOWWWWW~~~~ "+entity1);
        log.info("HEELLLLOOOOOOWWWWW~~~222~ "+ entity.getStart_date().atZone(ZoneId.systemDefault()).toEpochSecond());
//      assertThat(entity1.get(0).getStart_date(), is(entity.getStart_date()));
    }
}

