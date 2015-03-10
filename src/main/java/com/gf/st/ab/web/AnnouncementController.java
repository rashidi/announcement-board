package com.gf.st.ab.web;

import com.gf.st.ab.domain.Announcement;
import com.gf.st.ab.domain.UserRepository;
import com.gf.st.ab.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Rashidi Zin
 */
@RestController
@Secured("ROLE_USER")
@RequestMapping("announcements")
public class AnnouncementController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnnouncementService service;

    @RequestMapping(method = {POST, PUT})
    public ResponseEntity<Announcement> create(@RequestBody Announcement announcement) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        com.gf.st.ab.domain.User persistedUser = userRepository.findOneByUsernameIgnoreCase(loggedInUser.getUsername());

        try {
            announcement = service.create(persistedUser, announcement);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Announcement>(UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<Announcement>(announcement, CREATED);
    }

    @RequestMapping(value = "{id}", method = GET)
    public ResponseEntity<Announcement> get(@PathVariable(value = "id") String id) {

        Announcement announcement = service.get(id);

        if (announcement == null) { return new ResponseEntity<Announcement>(NOT_FOUND); }

        return new ResponseEntity<Announcement>(announcement, OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Page<Announcement>> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "25") int size) {

        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "created");

        return new ResponseEntity<Page<Announcement>>(service.getAll(pageable), OK);
    }

    @RequestMapping(value = "search", method = GET)
    public ResponseEntity<Page<Announcement>> getWithCriteria(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "content", required = false) String content,
                                                              @RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "username", required = false) String username,
                                                              @RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "25") int size) {

        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "created");

        return new ResponseEntity<Page<Announcement>>(service.search(title, content, userId, username, pageable), OK);
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") String id) {

        Announcement announcement = service.get(id);

        if (announcement == null) { return new ResponseEntity(NOT_FOUND); }

        service.delete(announcement);

        return new ResponseEntity(NO_CONTENT);
    }
}
