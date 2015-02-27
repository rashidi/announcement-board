package com.gf.st.ab.service;

import com.gf.st.ab.domain.User;
import com.gf.st.ab.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rashidi Zin
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User create(User user) throws Exception {

        if (exists(user)) { throw new Exception("username / email is already existed"); }

        return repository.save(user);
    }

    private boolean exists(User user) {
        return repository.findOneByEmailIgnoreCase(user.getEmail()) != null || repository.findOneByUsernameIgnoreCase(user.getUsername()) != null;
    }
}
