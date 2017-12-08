package com.sfl.nms.services.notification.email.template;

import com.sfl.nms.services.notification.email.template.model.EmailTemplateModel;
import com.sfl.nms.services.notification.email.template.model.NotificationTemplateType;
import com.sfl.nms.services.notification.model.email.EmailNotification;

import javax.annotation.Nonnull;

/**
 * User: Davit Yeghiazaryan
 * Company: SFL LLC
 * Date 6/13/16
 * Time 5:54 PM
 */
@FunctionalInterface
public interface TemplateEmailService {
    /**
     * Send templated email
     * @param from
     * @param to
     * @param templateType
     * @param emailTemplateModel
     *
     * @return EmailNotification
     */
    @Nonnull
    EmailNotification sendTemplatedEmail(final String from, final String to, final NotificationTemplateType templateType, final EmailTemplateModel emailTemplateModel);
}
