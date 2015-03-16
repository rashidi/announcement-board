package com.gf.st.ab.web.websocket;

import com.gf.st.ab.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * @author Rashidi Zin
 */
@Controller
public class AnnouncementListener implements ApplicationListener<AfterSaveEvent> {

    @Autowired
    SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/websocket/tracker")
    @SendTo("/topic/announcements")
    public Announcement receiveActivity(Announcement announcement) {
        return announcement;
    }

    @Override
    public void onApplicationEvent(AfterSaveEvent event) {
        if (event.getSource() instanceof Announcement) {
            Announcement announcement = (Announcement) event.getSource();
            messagingTemplate.convertAndSend("/topic/announcements", announcement);
        }
    }
}