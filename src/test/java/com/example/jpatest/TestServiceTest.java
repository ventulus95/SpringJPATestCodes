package com.example.jpatest;

import com.example.jpatest.action2.TestEntity;
import com.example.jpatest.action2.TestRepository;
import com.example.jpatest.action2.TestService1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TestServiceTest {

//    @Autowired
//    TestService testService;

    @Autowired
    @Qualifier(value = "ts")
    TestService1 testService;

    @Autowired
    TestRepository testRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void rollBackTest() throws Exception {

        TestEntity test = new TestEntity();
        test.setName("바보");
        TestEntity save = testRepository.save(test);
        //'바보' 라는 TestEntity가 저장


        assertThrows(IllegalStateException.class, ()-> testService.test());
        //'바보' 를 '멍청이'로 바꾸려는데 RunTimeException이 발생 여기서 이미 롤백해야하는 상황이지만...

        TestEntity test1 = testRepository.findById(save.getId()).get();
        assertEquals("바보", test1.getName());
        System.out.println(test1.getName()); //실제로는 롤백되지 않은 상황이 보여진다고?
        //'멍청이' 가 출력...ㅠ
    }

}