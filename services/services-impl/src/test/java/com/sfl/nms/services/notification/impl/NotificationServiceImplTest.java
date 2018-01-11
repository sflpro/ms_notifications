package com.sfl.nms.services.notification.impl;

import com.sfl.nms.services.notification.model.Notification;
import com.sfl.nms.persistence.repositories.notification.AbstractNotificationRepository;
import com.sfl.nms.persistence.repositories.notification.NotificationRepository;
import org.easymock.Mock;
import org.easymock.TestSubject;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 3/21/15
 * Time: 8:27 PM
 */
public class NotificationServiceImplTest extends AbstractNotificationServiceImplTest<Notification> {

    /* Test subject and mocks */
    @TestSubject
    private NotificationServiceImpl notificationService = new NotificationServiceImpl();

    @Mock
    private NotificationRepository notificationRepository;

    /* Constructors */
    public NotificationServiceImplTest() {
    }

    /* Utility methods */
    @Override
    protected AbstractNotificationRepository<Notification> getRepository() {
        return notificationRepository;
    }

    @Override
    protected AbstractNotificationServiceImpl<Notification> getService() {
        return notificationService;
    }

    @Override
    protected Class<Notification> getInstanceClass() {
        return Notification.class;
    }

    @Override
    protected Notification getInstance() {
        return getServicesImplTestHelper().createEmailNotification();
    }

}
