package com.company.product.dao;

import com.company.product.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public Long persist(User user) {
        em.persist(user);
        return user.getId();
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }
}
