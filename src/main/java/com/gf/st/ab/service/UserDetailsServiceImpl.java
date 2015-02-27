package com.gf.st.ab.service;

import com.gf.st.ab.domain.User;
import com.gf.st.ab.domain.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author Rashidi Zin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        EmailValidator emailValidator = new EmailValidator();

        if (isEmpty(username)) { throw new IllegalArgumentException("username is required"); }

        if (emailValidator.isValid(username, null)) { user = userRepository.findOneByEmailIgnoreCase(username); }

        else { user = userRepository.findOneByUsernameIgnoreCase(username); }

        if (user == null) { throw new UsernameNotFoundException("invalid username"); }

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(),
                commaSeparatedStringToAuthorityList("ROLE_USER")
        );
    }
}
