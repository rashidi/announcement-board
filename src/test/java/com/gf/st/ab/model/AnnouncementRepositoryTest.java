package com.gf.st.ab.model;

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
    AnnouncementRepository $;

    final Pageable pageable = new PageRequest(0, 1);
    Announcement announcement = new Announcement("Integration Test", "Executing integration test!");

    @Before
    public void init() {
        announcement = $.save(announcement);
    }

    @Test
    public void findAllByCreated() {
        assertTrue(
                $.findAllByCreated(announcement.getCreated(), pageable)
                        .hasContent()
        );
    }

    @Test
    public void findAllByMessageContaining() {
        assertTrue(
                $.findAllByMessageContaining(announcement.getMessage().substring(0, 10), pageable)
                        .hasContent()
        );
    }

    @After
    public void delete() {
        $.delete(announcement.getId());
    }
}
