package com.gf.st.ab.service;

import com.gf.st.ab.domain.Announcement;
import com.gf.st.ab.domain.AnnouncementRepository;
import com.gf.st.ab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

import static org.springframework.util.Assert.hasLength;

/**
 * @author Rashidi Zin
 */
@Service
public class AnnouncementService {

    @Autowired
    AnnouncementRepository repository;

    public Announcement create(User user, Announcement announcement) {

        Assert.hasText(announcement.getTitle(), "title is required");
        Assert.hasText(announcement.getContent(), "content is required");

        announcement.setUserId(user.getId());
        announcement.setUsername(user.getUsername());

        return repository.save(announcement);
    }

    public Announcement get(String id) {

        hasLength(id, "id is required");

        return repository.findOne(id);
    }

    public Page<Announcement> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Announcement> search(String title, String content, String userId, String username, Pageable pageable) {

        if (StringUtils.hasText(userId)) { return repository.findAllByUserId(userId, pageable); }

        else if (StringUtils.hasText(username)) { return repository.findAllByUsername(username, pageable); }

        else if (StringUtils.hasText(title)) { return repository.findAllByTitleContainsIgnoreCase(title, pageable); }

        else if (StringUtils.hasText(content)) { return repository.findAllByContentContainsIgnoreCase(content, pageable); }

        return new PageImpl<Announcement>(new ArrayList<Announcement>());
    }

    public void delete(Announcement announcement) {
        repository.delete(announcement);
    }
}
