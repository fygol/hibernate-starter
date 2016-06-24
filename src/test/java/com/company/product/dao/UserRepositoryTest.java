package com.company.product.dao;

import com.company.product.config.JpaConfig;
import com.company.product.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testCreate() {
        User user = new User();
        user.setName("John Smith");
        long userId = repository.persist(user);

        User actualUser = repository.find(userId);
        assertNotNull(actualUser);
    }
}
