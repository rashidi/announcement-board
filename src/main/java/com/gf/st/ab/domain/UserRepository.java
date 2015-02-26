package com.gf.st.ab.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Rashidi Zin
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findOneByEmailIgnoreCase(String email);

    User findOneByEmailIgnoreCaseAndPassword(String email, String password);

    User findOneByUsernameIgnoreCaseAndPassword(String username, String password);

    Page<User> findAllByStatus(UserStatus status, Pageable pageable);

    Page<User> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<User> findAllByUsernameContainsIgnoreCase(String username, Pageable pageable);
}
