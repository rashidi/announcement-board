package com.gf.st.ab.domain;

import com.gf.st.ab.AnnouncementBoardApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;

import static com.gf.st.ab.domain.UserStatus.ACTIVE;
import static com.gf.st.ab.domain.UserStatus.DORMANT;
import static org.junit.Assert.*;

/**
 * @author Rashidi Zin
 */
public class UserRepositoryTest extends AnnouncementBoardApplicationTests {

    @Autowired
    UserRepository $;

    private String id;
    private User user = new User("mohdrashidi.mohdzin@gfk.com", "rashidi.zin", "50m3r4n80m945$W0r8");
    private final Pageable pageable = new PageRequest(0, 100);

    @Before
    public void init() {
        id = $.save(user).getId();
    }

    @Test
    public void createWithInvalidEmail() {
        createForFailure("mohdrashidi.mohdzin", user.getUsername(), user.getPassword(), "email address is invalid");
    }

    @Test
    public void createWithoutUsername() {
        createForFailure(user.getEmail(), "", user.getPassword(), "username is required");
    }

    @Test
    public void createWithEmptyPassword() {
        createForFailure(user.getEmail(), user.getUsername(), "", "password is required");
    }

    @Test
    public void updateWithInvalidNameFormat() {
        try {
            user.setName("c3p0");
            $.save(user);
        } catch (ConstraintViolationException e) {
            assertEquals("invalid name format", e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    @Test
    public void findOneByEmailIgnoreCase() {
        String actual = $.findOneByEmailIgnoreCase(user.getEmail().toUpperCase()).getUsername();

        assertEquals(user.getUsername(), actual);
    }

    @Test
    public void findOneByEmailIgnoreCaseAndPassword() {
        String actual = $.findOneByEmailIgnoreCaseAndPassword(user.getEmail().toUpperCase(), user.getPassword()).getUsername();

        assertEquals(user.getUsername(), actual);
    }

    @Test
    public void findOneByUsernameIgnoreCaseAndPassword() {
        String actual = $.findOneByUsernameIgnoreCaseAndPassword(user.getUsername().toUpperCase(), user.getPassword()).getEmail();

        assertEquals(user.getEmail(), actual);
    }

    @Test
    public void findAllByStatus() {
        assertTrue($.findAllByStatus(ACTIVE, pageable).hasContent());
        assertFalse($.findAllByStatus(DORMANT, pageable).hasContent());
    }

    @Test
    public void findAllByNameContainsIgnoreCase() {
        user.setName("Rashidi Zin");

        $.save(user);

        assertTrue(
                $.findAllByNameContainsIgnoreCase(user.getName(), pageable)
                        .hasContent()
        );
        assertTrue(
                $.findAllByNameContainsIgnoreCase(user.getName().toUpperCase(), pageable)
                        .hasContent()
        );
        assertTrue(
                $.findAllByNameContainsIgnoreCase(user.getName().substring(2, 7), pageable)
                        .hasContent()
        );
    }

    private void createForFailure(String email, String username, String password, String expectedErrorMessage) {
        try {
            $.save(
                    new User(email, username, password)
            );
        } catch (ConstraintViolationException e) {
            assertEquals(expectedErrorMessage, e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    @After
    public void delete() {
        $.delete(id);
    }
}
