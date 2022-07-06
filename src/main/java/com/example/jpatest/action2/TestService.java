package com.example.jpatest.action2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {

    private final TestRepository testRepository;

    public void test(){
        testRepository.findAll().forEach( i -> i.setName("멍청이"));
        throw new IllegalStateException("언첵 예외 발생");
    }

    public void add() {

        TestEntity test = new TestEntity();
        test.setName("바보");
        testRepository.save(test);
    }
}