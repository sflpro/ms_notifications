package com.sfl.nms.services.notification.impl.email;

import com.sfl.nms.externalclients.email.mandrill.communicator.MandrillApiCommunicator;
import com.sfl.nms.externalclients.email.mandrill.model.request.SendEmailRequest;
import com.sfl.nms.services.notification.email.SmtpTransportService;
import com.sfl.nms.services.notification.model.Notification;
import com.sfl.nms.services.notification.model.NotificationProviderType;
import com.sfl.nms.services.notification.model.NotificationState;
import com.sfl.nms.persistence.utility.PersistenceUtilityService;
import com.sfl.nms.services.common.exception.ServicesRuntimeException;
import com.sfl.nms.services.notification.dto.email.MailSendConfiguration;
import com.sfl.nms.services.notification.email.EmailNotificationProcessor;
import com.sfl.nms.services.notification.email.EmailNotificationService;
import com.sfl.nms.services.notification.model.email.EmailNotification;
import com.sfl.nms.services.notification.model.email.ThirdPartyEmailNotification;
import com.sfl.nms.services.notification.model.email.ThirdPartyEmailNotificationProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/11/16
 * Time: 11:08 AM
 */
@Component
public class EmailNotificationProcessorImpl implements EmailNotificationProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationProcessorImpl.class);

    /* Dependencies */
    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    @Qualifier("smtpTransportService")
    private SmtpTransportService smtpTransportService;

    @Autowired
    private PersistenceUtilityService persistenceUtilityService;

    @Autowired(required = false)
    private MandrillApiCommunicator mandrillApiCommunicator;

    /* Constructors */
    public EmailNotificationProcessorImpl() {
    }


    @Override
    public void processNotification(@Nonnull final Long notificationId) {
        Assert.notNull(notificationId, "Email notification id should not be null");
        LOGGER.debug("Sending email notification for id - {}", notificationId);
        /* Retrieve email notification */
        final EmailNotification emailNotification = emailNotificationService.getNotificationById(notificationId);
        assertNotificationStateIsCreated(emailNotification);
        LOGGER.debug("Successfully retrieved email notification - {}", emailNotification);
        /* Update notification state to PROCESSING */
        updateEmailNotificationState(emailNotification.getId(), NotificationState.PROCESSING);
        try {
            /* Start processing external email service operation */
            if (NotificationProviderType.SMTP_SERVER.equals(emailNotification.getProviderType())) {
                final MailSendConfiguration mailSendConfiguration = createMailSendConfiguration(emailNotification);
                smtpTransportService.sendMessageOverSmtp(mailSendConfiguration);
                LOGGER.debug("Successfully sent email message configuration - {}, notification - ", mailSendConfiguration, emailNotification);
                /* Update state of notification to NotificationState.SENT */
                updateEmailNotificationState(emailNotification.getId(), NotificationState.SENT);
            }
            // Send email via mandrill
            else if (NotificationProviderType.MANDRILL.equals(emailNotification.getProviderType())) {
                final ThirdPartyEmailNotification thirdPartyEmailNotification = (ThirdPartyEmailNotification) emailNotification;
                thirdPartyEmailNotification.setSubject(thirdPartyEmailNotification.getSubject());
                // Create email data
                final SendEmailRequest emailRequest = createEmailRequest(thirdPartyEmailNotification);
                mandrillApiCommunicator.sendEmailTemplate(emailRequest);
                LOGGER.debug("Successfully sent email message for third party email notification - {}", thirdPartyEmailNotification);
                /* Update state of notification to NotificationState.SENT */
                updateEmailNotificationState(thirdPartyEmailNotification.getId(), NotificationState.SENT);
            }
        } catch (final Exception ex) {
            final String message = "Error occurred while sending email notification with id - " + emailNotification.getId();
            LOGGER.error(message, ex);
            /* Update state of notification to NotificationState.FAILED */
            updateEmailNotificationState(emailNotification.getId(), NotificationState.FAILED);
            throw new ServicesRuntimeException(message, ex);
        }
    }

    /* Utility methods */
    private MailSendConfiguration createMailSendConfiguration(final EmailNotification emailNotification) {
        final MailSendConfiguration mailSendConfiguration = new MailSendConfiguration();
        mailSendConfiguration.setContent(emailNotification.getContent());
        mailSendConfiguration.setTo(emailNotification.getRecipientEmail());
        mailSendConfiguration.setFrom(emailNotification.getSenderEmail());
        mailSendConfiguration.setSubject(emailNotification.getSubject());
        return mailSendConfiguration;
    }

    private void assertNotificationStateIsCreated(final Notification notification) {
        Assert.isTrue(notification.getState().equals(NotificationState.CREATED), "Notification state must be NotificationState.CREATED in order to proceed.");
    }

    private void updateEmailNotificationState(final Long notificationId, final NotificationState notificationState) {
        persistenceUtilityService.runInNewTransaction(() -> {
            emailNotificationService.updateNotificationState(notificationId, notificationState);
        });
    }

    private static SendEmailRequest createEmailRequest(final ThirdPartyEmailNotification thirdPartyEmailNotification) {
        final Set<ThirdPartyEmailNotificationProperty> properties = thirdPartyEmailNotification.getProperties();
        final Map<String, String> data = new HashMap<>();
        if (!CollectionUtils.isEmpty(properties)) {
            properties.forEach(property -> data.put(property.getPropertyKey(), property.getPropertyValue()));
        }
        return new SendEmailRequest(thirdPartyEmailNotification.getRecipientEmail(), thirdPartyEmailNotification.getTemplateName(), data);
    }

    public MandrillApiCommunicator getMandrillApiCommunicator() {
        return mandrillApiCommunicator;
    }

    public void setMandrillApiCommunicator(MandrillApiCommunicator mandrillApiCommunicator) {
        this.mandrillApiCommunicator = mandrillApiCommunicator;
    }
}
