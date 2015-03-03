package com.gf.st.ab.service;

import com.gf.st.ab.domain.User;
import com.gf.st.ab.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

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

    public User get(String id) {
        return repository.findOne(id);
    }

    public void delete(String id) throws Exception {

        if (isEmpty(id)) { throw new IllegalArgumentException("id is required"); }

        User user = repository.findOne(id);

        if (user == null) { throw new Exception("user not found"); }

        repository.delete(user);
    }

    private boolean exists(User user) {
        return repository.findOneByEmailIgnoreCase(user.getEmail()) != null || repository.findOneByUsernameIgnoreCase(user.getUsername()) != null;
    }
}
