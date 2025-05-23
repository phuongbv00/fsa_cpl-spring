package fsa.cplminiprj.repository.impl;

import fsa.cplminiprj.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepository<E, ID> implements CrudRepository<E, ID> {
    private final Class<E> eClass;

    @PersistenceContext
    protected EntityManager em;

    protected AbstractCrudRepository(Class<E> eClass) {
        this.eClass = eClass;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(em.find(eClass, id));
    }

    @Override
    public List<E> findAll() {
        String queryString = "select e from %s e".formatted(eClass.getSimpleName());
        return em.createQuery(queryString, eClass).getResultList();
    }

    @Override
    public void save(E entity) {
        em.persist(entity);
    }

    @Override
    public void update(ID id, E entity) {
        findById(id).ifPresent(__ -> em.merge(entity));
    }

    @Override
    public void delete(ID id) {
        findById(id).ifPresent(em::remove);
    }
}
