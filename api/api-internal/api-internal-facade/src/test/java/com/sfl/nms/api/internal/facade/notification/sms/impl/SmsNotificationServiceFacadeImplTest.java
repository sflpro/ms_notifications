package com.sfl.nms.api.internal.facade.notification.sms.impl;

import com.sfl.nms.api.internal.facade.test.AbstractFacadeUnitTest;
import com.sfl.nms.core.api.internal.model.common.result.ErrorType;
import com.sfl.nms.core.api.internal.model.common.result.ResultResponseModel;
import com.sfl.nms.core.api.internal.model.sms.SmsNotificationModel;
import com.sfl.nms.core.api.internal.model.sms.request.CreateSmsNotificationRequest;
import com.sfl.nms.core.api.internal.model.sms.response.CreateSmsNotificationResponse;
import com.sfl.nms.services.notification.UserNotificationService;
import com.sfl.nms.services.notification.dto.UserNotificationDto;
import com.sfl.nms.services.notification.dto.sms.SmsNotificationDto;
import com.sfl.nms.services.notification.event.sms.StartSendingNotificationEvent;
import com.sfl.nms.services.notification.model.NotificationProviderType;
import com.sfl.nms.services.notification.model.UserNotification;
import com.sfl.nms.services.notification.model.sms.SmsNotification;
import com.sfl.nms.services.notification.sms.SmsNotificationService;
import com.sfl.nms.services.system.event.ApplicationEventDistributionService;
import com.sfl.nms.services.user.UserService;
import com.sfl.nms.services.user.model.User;
import static org.easymock.EasyMock.*;
import org.easymock.Mock;
import org.easymock.TestSubject;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/13/16
 * Time: 5:57 PM
 */
public class SmsNotificationServiceFacadeImplTest extends AbstractFacadeUnitTest {


    /* Test subject and mocks */
    @TestSubject
    private SmsNotificationServiceFacadeImpl smsNotificationServiceFacade = new SmsNotificationServiceFacadeImpl();

    @Mock
    private SmsNotificationService smsNotificationService;

    @Mock
    private UserService userService;

    @Mock
    private UserNotificationService userNotificationService;

    @Mock
    private ApplicationEventDistributionService applicationEventDistributionService;

    /* Constructors */
    public SmsNotificationServiceFacadeImplTest() {
    }


    /* Test methods */
    @Test
    public void testCreateSmsNotificationWithInvalidArguments() {
        // Reset
        resetAll();
        // Replay
        replayAll();
        // Run test scenario
        try {
            smsNotificationServiceFacade.createSmsNotification(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateSmsNotificationWithValidationErrors() {
        // Test data
        final CreateSmsNotificationRequest request = new CreateSmsNotificationRequest();
        // Reset
        resetAll();
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateSmsNotificationResponse> result = smsNotificationServiceFacade.createSmsNotification(request);
        assertNotNull(result);
        assertNull(result.getResponse());
        assertNotNull(result.getErrors());
        assertErrorExists(result.getErrors(), ErrorType.NOTIFICATION_SMS_RECIPIENT_MISSING);
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateSmsNotificationWithoutUser() {
        // Test data
        final CreateSmsNotificationRequest request = getServiceFacadeImplTestHelper().createCreateSmsNotificationRequest();
        request.setUserUuId(null);
        final SmsNotificationDto smsNotificationDto = new SmsNotificationDto(request.getRecipientNumber(), NotificationProviderType.AMAZON_SNS, request.getBody(), request.getClientIpAddress());
        final Long notificationId = 1L;
        final SmsNotification smsNotification = getServiceFacadeImplTestHelper().createSmsNotification();
        smsNotification.setId(notificationId);
        // Reset
        resetAll();
        // Expectations
        expect(smsNotificationService.createSmsNotification(eq(smsNotificationDto))).andReturn(smsNotification).once();
        applicationEventDistributionService.publishAsynchronousEvent(eq(new StartSendingNotificationEvent(notificationId)));
        expectLastCall().once();
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateSmsNotificationResponse> result = smsNotificationServiceFacade.createSmsNotification(request);
        assertNotNull(result);
        assertNotNull(result.getResponse());
        assertNotNull(result.getErrors());
        assertEquals(0, result.getErrors().size());
        // Assert notification model
        getServiceFacadeImplTestHelper().assertSmsNotificationModel(smsNotification, (SmsNotificationModel) result.getResponse().getNotification());
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateSmsNotificationWithUser() {
        // Test data
        final CreateSmsNotificationRequest request = getServiceFacadeImplTestHelper().createCreateSmsNotificationRequest();
        final SmsNotificationDto smsNotificationDto = new SmsNotificationDto(request.getRecipientNumber(), NotificationProviderType.AMAZON_SNS, request.getBody(), request.getClientIpAddress());
        final Long notificationId = 1L;
        final SmsNotification smsNotification = getServiceFacadeImplTestHelper().createSmsNotification();
        smsNotification.setId(notificationId);
        final Long userId = 2L;
        final User user = getServiceFacadeImplTestHelper().createUser();
        user.setId(userId);
        final Long userNotificationId = 3L;
        final UserNotification userNotification = getServiceFacadeImplTestHelper().createUserNotification();
        userNotification.setId(userNotificationId);
        // Reset
        resetAll();
        // Expectations
        expect(smsNotificationService.createSmsNotification(eq(smsNotificationDto))).andReturn(smsNotification).once();
        expect(userService.getOrCreateUserForUuId(eq(request.getUserUuId()))).andReturn(user).once();
        expect(userNotificationService.createUserNotification(eq(userId), eq(notificationId), eq(new UserNotificationDto()))).andReturn(userNotification).once();
        applicationEventDistributionService.publishAsynchronousEvent(eq(new StartSendingNotificationEvent(notificationId)));
        expectLastCall().once();
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateSmsNotificationResponse> result = smsNotificationServiceFacade.createSmsNotification(request);
        assertNotNull(result);
        assertNotNull(result.getResponse());
        assertNotNull(result.getErrors());
        assertEquals(0, result.getErrors().size());
        // Assert notification model
        getServiceFacadeImplTestHelper().assertSmsNotificationModel(smsNotification, (SmsNotificationModel) result.getResponse().getNotification());
        // Verify
        verifyAll();
    }
}
