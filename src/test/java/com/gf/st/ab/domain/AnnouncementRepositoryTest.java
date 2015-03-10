package com.gf.st.ab.domain;

import com.gf.st.ab.AbstractTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertTrue;

/**
 * @author Rashidi Zin
 */
public class AnnouncementRepositoryTest extends AbstractTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnnouncementRepository $;

    User user;
    Announcement announcement;

    private final Pageable pageable = new PageRequest(0, 100);

    @Before
    public void init() {
        user = userRepository.save(new User("batman@gotham.com", "^darkKnight^", "!4m7h3847m4N"));
        announcement = $.save(new Announcement("All Men Have Limits", "All men have limits. They learn what they are and learn not to exceed them. I ignore mine.", user.getId(), user.getUsername()));
    }

    @Test
    public void findAllByUsername() {
        assertTrue(
                $.findAllByUsername(user.getUsername(), pageable).hasContent()
        );
    }

    @Test
    public void findAllByUserId() {
        assertTrue(
                $.findAllByUserId(user.getId(), pageable).hasContent()
        );
    }

    @Test
    public void findAllByTitleContainsIgnoreCase() {
        assertTrue(
                $.findAllByTitleContainsIgnoreCase(announcement.getTitle().substring(4, 12).toLowerCase(), pageable).hasContent()
        );
    }

    @Test
    public void findAllByContentContainsIgnoreCase() {
        assertTrue(
                $.findAllByContentContainsIgnoreCase(announcement.getContent().substring(76, 84).toLowerCase(), pageable).hasContent()
        );
    }

    @After
    public void delete() {
        $.delete(announcement);
        userRepository.delete(user);
    }
}
