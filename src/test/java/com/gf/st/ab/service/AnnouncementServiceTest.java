package com.gf.st.ab.service;

import com.gf.st.ab.AbstractMockTests;
import com.gf.st.ab.domain.Announcement;
import com.gf.st.ab.domain.AnnouncementRepository;
import com.gf.st.ab.domain.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * @author Rashidi Zin
 */
public class AnnouncementServiceTest extends AbstractMockTests {

    @Mock
    AnnouncementRepository repository;

    @InjectMocks
    AnnouncementService $;

    Pageable pageable = new PageRequest(0, 10);
    Page<Announcement> searchResult = new PageImpl<Announcement>(new ArrayList<Announcement>());

    private User user = new User("cyc7095Fr0mxM3n", "scott@xavier-school.com", "cyclop", "1w!7Hp0w3rFul3y3");
    private Announcement announcement = new Announcement(
            "Really Annoys Me",
            "This is the bit that really annoys me. All the things in this world that can be fixed with money? " +
                    "And every time it's \"Well, I've got all this cash, but I bought myself an asteroid hideout instead.\"",
            user.getId(),
            user.getUsername()
    );

    @Test
    public void create() {
        when(repository.save(announcement)).thenReturn(announcement);

        $.create(user, announcement);

        verify(repository).save(announcement);
    }

    @Test
    public void createWithoutTitle() {
        expect.expect(IllegalArgumentException.class);
        expect.expectMessage("title is required");

        announcement.setTitle("");

        $.create(user, announcement);
    }

    @Test
    public void createWithoutContent() {
        expect.expect(IllegalArgumentException.class);
        expect.expectMessage("content is required");

        announcement.setContent("");

        $.create(user, announcement);
    }

    @Test
    public void get() {
        announcement.setId("5c077th3cYcl0p54!d");

        when(repository.findOne(announcement.getId())).thenReturn(announcement);

        $.get(announcement.getId());

        verify(repository).findOne(announcement.getId());
    }

    @Test
    public void getAll() {
        when(repository.findAll(pageable)).thenReturn(searchResult);

        $.getAll(pageable);

        verify(repository).findAll(pageable);
    }

    @Test
    public void searchByUserId() {
        when(repository.findAllByUserId(user.getId(), pageable)).thenReturn(searchResult);

        $.search(null, null, user.getId(), null, pageable);

        verify(repository).findAllByUserId(user.getId(), pageable);
        verify(repository, times(0)).findAllByUsername(user.getUsername(), pageable);
        verify(repository, times(0)).findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable);
        verify(repository, times(0)).findAllByContentContainsIgnoreCase(announcement.getContent(), pageable);
    }

    @Test
    public void searchByUserName() {
        when(repository.findAllByUsername(user.getUsername(), pageable)).thenReturn(searchResult);

        $.search(null, null, null, user.getUsername(), pageable);

        verify(repository).findAllByUsername(user.getUsername(), pageable);
        verify(repository, times(0)).findAllByUserId(user.getId(), pageable);
        verify(repository, times(0)).findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable);
        verify(repository, times(0)).findAllByContentContainsIgnoreCase(announcement.getContent(), pageable);
    }

    @Test
    public void searchByTitle() {
        when(repository.findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable)).thenReturn(searchResult);

        $.search(announcement.getTitle(), null, null, null, pageable);

        verify(repository).findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable);
        verify(repository, times(0)).findAllByUsername(user.getUsername(), pageable);
        verify(repository, times(0)).findAllByUserId(user.getId(), pageable);
        verify(repository, times(0)).findAllByContentContainsIgnoreCase(announcement.getContent(), pageable);
    }

    @Test
    public void searchByContent() {
        when(repository.findAllByContentContainsIgnoreCase(announcement.getTitle(), pageable)).thenReturn(searchResult);

        $.search(null, announcement.getContent(), null, null, pageable);

        verify(repository).findAllByContentContainsIgnoreCase(announcement.getContent(), pageable);
        verify(repository, times(0)).findAllByUserId(user.getId(), pageable);
        verify(repository, times(0)).findAllByUsername(user.getUsername(), pageable);
        verify(repository, times(0)).findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable);
    }

    @Test
    public void searchWithoutParameter() {
        $.search(null, null, null, null, pageable);

        verify(repository, times(0)).findAllByUserId(user.getId(), pageable);
        verify(repository, times(0)).findAllByUsername(user.getUsername(), pageable);
        verify(repository, times(0)).findAllByTitleContainsIgnoreCase(announcement.getTitle(), pageable);
        verify(repository, times(0)).findAllByContentContainsIgnoreCase(announcement.getContent(), pageable);
    }

    @Test
    public void delete() {
        $.delete(announcement);

        verify(repository).delete(announcement);
    }
}
