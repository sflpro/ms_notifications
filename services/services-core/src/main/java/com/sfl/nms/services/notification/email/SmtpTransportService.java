package com.sfl.nms.services.notification.email;

import com.sfl.nms.services.notification.dto.email.MailSendConfiguration;

import javax.annotation.Nonnull;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/11/16
 * Time: 10:55 AM
 */

public interface SmtpTransportService {


    /**
     * Perform email over SMTP
     *
     * @param mailSendConfiguration
     */
    void sendMessageOverSmtp(@Nonnull final MailSendConfiguration mailSendConfiguration);
}
