package com.sfl.nms.services.notification.impl;

import com.sfl.nms.services.notification.model.Notification;
import com.sfl.nms.persistence.repositories.notification.AbstractNotificationRepository;
import com.sfl.nms.persistence.repositories.notification.NotificationRepository;
import com.sfl.nms.services.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 3/21/15
 * Time: 8:02 PM
 */
@Service
public class NotificationServiceImpl extends AbstractNotificationServiceImpl<Notification> implements NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    /* Dependencies */
    @Autowired
    private NotificationRepository notificationRepository;

    /* Constructors */
    public NotificationServiceImpl() {
        LOGGER.debug("Initializing notification service");
    }

    /* Utility methods */
    @Override
    protected AbstractNotificationRepository<Notification> getRepository() {
        return notificationRepository;
    }

    @Override
    protected Class<Notification> getInstanceClass() {
        return Notification.class;
    }

}
