package fsa.cplminiprj.repository.impl;

import fsa.cplminiprj.dto.UserStatusStats;
import fsa.cplminiprj.entity.User;
import fsa.cplminiprj.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
//        Opt 1: HQL/JPQL
//        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
//        query.setParameter("username", username);

//        Opt 2: Native query
        Query query = em.createNativeQuery("SELECT * FROM users where username = ?", User.class);
        query.setParameter(1, username);
        return Optional.ofNullable((User) query.getSingleResult());
    }

    @Override
    public List<User> search(String keyword) {
//        Opt 1: Native query
//        Query query = em.createNativeQuery("SELECT * FROM users where username like ? or password like ? or id like ? or status like ?", User.class);
//        query.setParameter(1, "%" + keyword + "%");
//        query.setParameter(2, "%" + keyword + "%");
//        query.setParameter(3, "%" + keyword + "%");
//        query.setParameter(4, "%" + keyword + "%");

//        Opt 2: HQL
        TypedQuery<User> query = em.createQuery("select u from User u where u.username like :k or u.password like :k or cast(u.id as string) like :k or cast(u.status as string) like :k", User.class);
        query.setParameter("k", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public int count() {
//        Opt 1: Native query
//        Query query = em.createNativeQuery("SELECT COUNT(*) FROM users");
//        return ((Long) query.getSingleResult()).intValue();

//        Opt 2: HQL
        TypedQuery<Long> query = em.createQuery("select count(u) from User u", Long.class);
        return query.getSingleResult().intValue();
    }

    @Override
    public List<UserStatusStats> getStatusStats() {
//        Opt 1: Native query + Object[]
//        Query query = em.createNativeQuery("SELECT status, COUNT(*) FROM users GROUP BY status", Object[].class);
//        List<Object[]> resultList = query.getResultList();
//        return resultList.stream()
//                .map(tuple -> {
//                    UserStatusStats stats = new UserStatusStats();
//                    stats.setStatus((int) tuple[0]);
//                    stats.setCount(((Long) tuple[1]).intValue());
//                    return stats;
//                })
//                .toList();

//        Opt 2: Native query + Tuple
//        Query query = em.createNativeQuery("SELECT status, COUNT(*) as status_count FROM users GROUP BY status", Tuple.class);
//        List<Tuple> resultList = query.getResultList();
//        return resultList.stream()
//                .map(tuple -> {
//                    UserStatusStats stats = new UserStatusStats();
////                    stats.setStatus(tuple.get(0, Integer.class));
//                    stats.setStatus(tuple.get("status", Integer.class));
////                    stats.setCount(tuple.get(1, Long.class).intValue());
//                    stats.setCount(tuple.get("status_count", Long.class).intValue());
//                    return stats;
//                })
//                .toList();

//        Opt 3: HQL
        TypedQuery<UserStatusStats> query = em
                .createQuery("select new fsa.cplminiprj.dto.UserStatusStats(u.status, cast(count(u) as int)) from User u group by u.status", UserStatusStats.class);
        return query.getResultList();
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
