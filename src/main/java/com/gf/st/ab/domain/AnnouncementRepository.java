package com.gf.st.ab.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Rashidi Zin
 */
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {

    Page<Announcement> findAllByUsername(String username, Pageable pageable);

    Page<Announcement> findAllByUserId(String userId, Pageable pageable);

    Page<Announcement> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);

    Page<Announcement> findAllByContentContainsIgnoreCase(String content, Pageable pageable);
}
