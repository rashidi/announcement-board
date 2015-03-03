package com.gf.st.ab.web;

import com.gf.st.ab.domain.User;
import com.gf.st.ab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Rashidi Zin
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(method = { PUT, POST })
    public ResponseEntity<User> create(@RequestBody @Valid User user) {

        try {
            user = service.create(user);
        } catch (Exception e) {
            return new ResponseEntity(user, CONFLICT);
        }

        return new ResponseEntity(user, CREATED);
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = GET, value = "{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        User user = service.get(id);

        return (user != null) ? new ResponseEntity(user, OK) : new ResponseEntity(NOT_FOUND);
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        User user = service.get(id);

        if (user == null) { return new ResponseEntity(NOT_FOUND); }

        service.delete(user.getId());

        return new ResponseEntity(NO_CONTENT);
    }
}
