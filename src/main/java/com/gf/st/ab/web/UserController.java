package com.gf.st.ab.web;

import com.gf.st.ab.domain.User;
import com.gf.st.ab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Rashidi Zin
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(method = PUT)
    public ResponseEntity create(@RequestBody @Valid User user) {

        try {
            user = service.create(user);
        } catch (Exception e) {
            return new ResponseEntity(user, CONFLICT);
        }

        return new ResponseEntity(user, CREATED);
    }
}
