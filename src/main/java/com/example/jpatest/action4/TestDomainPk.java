package com.example.jpatest.action4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDomainPk implements Serializable {

    private Long id;
    private Long id2;
    private Long id3;
    private Long id4;

}
