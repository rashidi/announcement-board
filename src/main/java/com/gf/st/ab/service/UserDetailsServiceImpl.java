package com.gf.st.ab.service;

import com.gf.st.ab.domain.Authorization;
import com.gf.st.ab.domain.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

/**
 * @author Rashidi Zin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthorizationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authorization token = repository.findOne(username);

        if (token == null) { throw new UsernameNotFoundException("invalid username"); }

        return new User(token.getUsername(), token.getToken(), commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
