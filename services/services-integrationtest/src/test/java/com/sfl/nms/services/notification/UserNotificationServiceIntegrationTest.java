package com.sfl.nms.services.notification;

import com.sfl.nms.services.notification.model.Notification;
import com.sfl.nms.services.notification.model.UserNotification;
import com.sfl.nms.services.test.AbstractServiceIntegrationTest;
import com.sfl.nms.services.user.model.User;
import com.sfl.nms.services.notification.dto.UserNotificationDto;
import com.sfl.nms.services.notification.exception.UserNotificationAlreadyExistsException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 4/3/15
 * Time: 12:49 PM
 */
public class UserNotificationServiceIntegrationTest extends AbstractServiceIntegrationTest {

    /* Dependencies */
    @Autowired
    private UserNotificationService userNotificationService;

    /* Constructors */
    public UserNotificationServiceIntegrationTest() {
    }

    /* Test methods */
    @Test
    public void testGetUserNotificationById() {
        // Prepare data
        final UserNotification userNotification = getServicesTestHelper().createUserNotification();
        // Load user notification
        UserNotification result = userNotificationService.getUserNotificationById(userNotification.getId());
        assertEquals(userNotification, result);
        // Flush, clear and assert
        flushAndClear();
        result = userNotificationService.getUserNotificationById(userNotification.getId());
        assertEquals(userNotification, result);
    }

    @Test
    public void testCreateUserNotification() {
        // Test data
        final User user = getServicesTestHelper().createUser();
        final Notification notification = getServicesTestHelper().createSmsNotification();
        final UserNotificationDto userNotificationDto = getServicesTestHelper().createUserNotificationDto();
        flushAndClear();
        // Create user notification
        UserNotification userNotification = userNotificationService.createUserNotification(user.getId(), notification.getId(), userNotificationDto);
        assertUserNotification(userNotification, userNotificationDto, user, notification);
        // Flush, clear, reload and assert again
        flushAndClear();
        userNotification = userNotificationService.getUserNotificationById(userNotification.getId());
        assertUserNotification(userNotification, userNotificationDto, user, notification);
    }

    @Test
    public void testCreateUserNotificationWhenItAlreadyExistsForNotification() {
        // Test data
        final User user = getServicesTestHelper().createUser();
        final Notification notification = getServicesTestHelper().createSmsNotification();
        final UserNotificationDto userNotificationDto = getServicesTestHelper().createUserNotificationDto();
        flushAndClear();
        // Create user notification
        UserNotification userNotification = userNotificationService.createUserNotification(user.getId(), notification.getId(), userNotificationDto);
        assertNotNull(userNotification);
        try {
            userNotificationService.createUserNotification(user.getId(), notification.getId(), userNotificationDto);
            fail("Exception should be thrown");
        } catch (final UserNotificationAlreadyExistsException ex) {
            // Expected
        }
        // Flush, clear, reload and assert again
        flushAndClear();
        try {
            userNotificationService.createUserNotification(user.getId(), notification.getId(), userNotificationDto);
            fail("Exception should be thrown");
        } catch (final UserNotificationAlreadyExistsException ex) {
            // Expected
        }
    }

    /* Utility methods */
    private void assertUserNotification(final UserNotification userNotification, final UserNotificationDto userNotificationDto, final User user, final Notification notification) {
        getServicesTestHelper().assertUserNotification(userNotification, userNotificationDto);
        assertNotNull(userNotification.getUser());
        assertEquals(user.getId(), userNotification.getUser().getId());
        assertNotNull(userNotification.getNotification());
        assertEquals(notification.getId(), userNotification.getNotification().getId());
    }
}
