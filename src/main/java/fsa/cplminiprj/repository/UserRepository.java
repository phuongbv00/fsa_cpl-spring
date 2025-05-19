package fsa.cplminiprj.repository;

import fsa.cplminiprj.dto.UserStatusStats;
import fsa.cplminiprj.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> search(String keyword);
    int count();
    List<UserStatusStats> getStatusStats();
}
