package com.sfl.nms.services.notification.impl.email;

import com.sfl.nms.services.notification.dto.email.EmailNotificationDto;
import com.sfl.nms.services.notification.impl.AbstractNotificationServiceImpl;
import com.sfl.nms.services.notification.model.email.EmailNotification;
import com.sfl.nms.persistence.repositories.notification.AbstractNotificationRepository;
import com.sfl.nms.persistence.repositories.notification.email.EmailNotificationRepository;
import com.sfl.nms.services.notification.impl.AbstractNotificationServiceImplTest;
import static org.easymock.EasyMock.*;
import org.easymock.Mock;
import org.easymock.TestSubject;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 3/21/15
 * Time: 8:32 PM
 */
public class EmailNotificationServiceImplTest extends AbstractNotificationServiceImplTest<EmailNotification> {

    /* Test subject and mocks */
    @TestSubject
    private EmailNotificationServiceImpl emailNotificationService = new EmailNotificationServiceImpl();

    @Mock
    private EmailNotificationRepository emailNotificationRepository;

    /* Constructors */
    public EmailNotificationServiceImplTest() {
    }

    /* Test methods */
    @Test
    public void testCreateEmailNotificationWithInvalidArguments() {
        // Test data
        final EmailNotificationDto emailNotificationDto = getServicesImplTestHelper().createEmailNotificationDto();
        // Reset
        resetAll();
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailNotificationService.createEmailNotification(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        try {
            emailNotificationService.createEmailNotification(new EmailNotificationDto(null, emailNotificationDto.getSenderEmail(), emailNotificationDto.getProviderType(), emailNotificationDto.getContent(), emailNotificationDto.getSubject(), emailNotificationDto.getClientIpAddress()));
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        try {
            emailNotificationService.createEmailNotification(new EmailNotificationDto(emailNotificationDto.getRecipientEmail(), null, emailNotificationDto.getProviderType(), emailNotificationDto.getContent(), emailNotificationDto.getSubject(), emailNotificationDto.getClientIpAddress()));
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        try {
            emailNotificationService.createEmailNotification(new EmailNotificationDto(emailNotificationDto.getRecipientEmail(), emailNotificationDto.getSenderEmail(), null, emailNotificationDto.getContent(), emailNotificationDto.getSubject(), emailNotificationDto.getClientIpAddress()));
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
    }

    @Test
    public void testCreateEmailNotification() {
        // Test data
        final EmailNotificationDto emailNotificationDto = getServicesImplTestHelper().createEmailNotificationDto();
        // Reset
        resetAll();
        // Expectations
        expect(emailNotificationRepository.save(isA(EmailNotification.class))).andAnswer(() -> (EmailNotification) getCurrentArguments()[0]).once();
        // Replay
        replayAll();
        // Run test scenario
        final EmailNotification emailNotification = emailNotificationService.createEmailNotification(emailNotificationDto);
        getServicesImplTestHelper().assertEmailNotification(emailNotification, emailNotificationDto);
    }

    /* Utility methods */
    @Override
    protected AbstractNotificationRepository<EmailNotification> getRepository() {
        return emailNotificationRepository;
    }

    @Override
    protected AbstractNotificationServiceImpl<EmailNotification> getService() {
        return emailNotificationService;
    }

    @Override
    protected Class<EmailNotification> getInstanceClass() {
        return EmailNotification.class;
    }

    @Override
    protected EmailNotification getInstance() {
        return getServicesImplTestHelper().createEmailNotification();
    }

}
