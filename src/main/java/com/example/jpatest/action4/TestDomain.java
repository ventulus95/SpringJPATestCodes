package com.example.jpatest.action4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@IdClass(TestDomainPk.class)
public class TestDomain {

    @Id
    private Long id;

    @Id
    @Column
    private Long id2;

    @Id
    @Column
    private Long id3;

    @Id
    @Column
    private Long id4;

}
