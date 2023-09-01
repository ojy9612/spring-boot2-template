package com.zeki.springboot2template.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "dbNameEntityManager")
    private EntityManager dbNameEntityManager;

    @Bean
    public JPAQueryFactory dbNameJpaQueryFactory() {
        return new JPAQueryFactory(dbNameEntityManager);
    }
}

