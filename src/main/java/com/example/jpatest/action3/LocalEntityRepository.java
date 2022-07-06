package com.example.jpatest.action3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalEntityRepository extends JpaRepository<LocalEntity, Long> {

    @Query(value = "SELECT UNIX_TIMESTAMP(start_date) FROM local_entity where eid = :id LIMIT 1",nativeQuery = true)
//    @Query(value = "SELECT * FROM local_entity;",nativeQuery = true)
    Long query1(@Param("id") Long id);
}
