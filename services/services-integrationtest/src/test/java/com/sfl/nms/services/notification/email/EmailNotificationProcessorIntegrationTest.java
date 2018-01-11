package com.sfl.nms.services.notification.email;

import com.sfl.nms.services.notification.model.NotificationState;
import com.sfl.nms.services.notification.model.email.EmailNotification;
import com.sfl.nms.services.test.AbstractServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/11/16
 * Time: 6:49 PM
 */
public class EmailNotificationProcessorIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private EmailNotificationProcessor emailNotificationProcessor;

    @Autowired
    private EmailNotificationService emailNotificationService;

    /* Constructors */
    public EmailNotificationProcessorIntegrationTest() {
    }

    /* Test methods */
    @Test
    public void testProcessPushNotification() {
        // Prepare data
        EmailNotification emailNotification = getServicesTestHelper().createEmailNotification();
        Assert.assertEquals(NotificationState.CREATED, emailNotification.getState());
        // Process push notification
        emailNotificationProcessor.processNotification(emailNotification.getId());
        flushAndClear();
        // Reload push notification
        emailNotification = emailNotificationService.getNotificationById(emailNotification.getId());
        Assert.assertEquals(NotificationState.SENT, emailNotification.getState());
    }
}
