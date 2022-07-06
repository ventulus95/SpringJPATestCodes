package com.example.jpatest.action4;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDomainRepository extends JpaRepository<TestDomain, TestDomainPk> {
}
