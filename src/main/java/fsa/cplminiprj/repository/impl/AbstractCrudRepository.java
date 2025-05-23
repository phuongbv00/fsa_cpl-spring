package fsa.cplminiprj.repository.impl;

import fsa.cplminiprj.repository.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepository<E, ID> implements CrudRepository<E, ID> {
    private final Class<E> eClass;

    protected abstract EntityManager getEntityManager();

    protected AbstractCrudRepository(Class<E> eClass) {
        this.eClass = eClass;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.ofNullable(getEntityManager().find(eClass, id));
    }

    @Override
    public List<E> findAll() {
        String queryString = "select e from %s e".formatted(eClass.getSimpleName());
        return getEntityManager().createQuery(queryString, eClass).getResultList();
    }

    @Override
    public void save(E entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(ID id, E entity) {
        findById(id).ifPresent(__ -> getEntityManager().merge(entity));
    }

    @Override
    public void delete(ID id) {
        findById(id).ifPresent(getEntityManager()::remove);
    }
}
