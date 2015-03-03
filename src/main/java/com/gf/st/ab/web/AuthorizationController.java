package com.gf.st.ab.web;

import com.gf.st.ab.domain.Authorization;
import com.gf.st.ab.domain.AuthorizationRepository;
import com.gf.st.ab.domain.User;
import com.gf.st.ab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Rashidi Zin
 */
@RestController
@RequestMapping("authorization")
public class AuthorizationController {

    @Autowired
    AuthorizationRepository repository;

    @Autowired
    UserService userService;

    @RequestMapping(method = POST, value = "login")
    public ResponseEntity<Authorization> login(@RequestBody User user) {
        User persisted = userService.authenticate(user.getUsername(), user.getPassword());

        if (persisted == null) { return new ResponseEntity(user, FORBIDDEN); }

        String token = KeyGenerators.string().generateKey();

        Authorization authorization = repository.save(
                new Authorization(persisted.getUsername(), token)
        );

        repository.save(authorization);
        return new ResponseEntity(authorization, OK);
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = POST, value = "logout")
    public ResponseEntity logout() {
        org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        repository.delete(
                new Authorization(loggedInUser.getUsername(), loggedInUser.getPassword())
        );

        return new ResponseEntity(OK);
    }
}
