package org.example.txbalance.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    // kafka transaction manager 가 jpa tx manager 와 동일한 PlatformTransactionManager 를 구현하고 있어,
    // 동시에 사용할 때 에러가 발생함.
    // 따라서 직접 등록하고 @Transactional 어노테이션에서 transactionManager를 직접 지정하여 사용해야함.

    @Bean(name = "transactionManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
