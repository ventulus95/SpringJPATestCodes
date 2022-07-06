package com.example.jpatest.action2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "ts")
@RequiredArgsConstructor
public class TestService1 {

    private final TestRepository testRepository;

    @Transactional
    public void test() {
        testRepository.findAll().forEach(i -> i.setName("멍청이"));
        throw new IllegalStateException("언첵 예외 발생");
    }
}
