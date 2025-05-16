package fsa.cplminiprj.repository.impl;

import fsa.cplminiprj.entity.User;
import fsa.cplminiprj.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public void save(User entity) {
        em.persist(entity);
    }

    @Override
    public void update(Long id, User entity) {
        findById(id).ifPresent(em::merge);
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(em::remove);
    }
}
