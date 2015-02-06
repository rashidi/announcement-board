package com.gf.st.ab.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

/**
 * @author Rashidi Zin
 */
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {

    Page<Announcement> findAllByCreated(Date created, Pageable pageable);

    Page<Announcement> findAllByMessageContaining(String message, Pageable pageable);
}
