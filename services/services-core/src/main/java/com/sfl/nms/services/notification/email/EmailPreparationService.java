package com.sfl.nms.services.notification.email;

import com.sfl.nms.services.notification.email.template.model.forgotpassword.ResetPasswordEmailTemplateModel;
import com.sfl.nms.services.notification.model.email.EmailNotification;

import javax.annotation.Nonnull;

/**
 * Created by Martirosyan
 * on 6/15/16.
 */
public interface EmailPreparationService  {

    /**
     * Prepare reset password email template model and send email
     *
     * @param toAddress
     * @param resetPasswordEmailTemplateModel
     */
    @Nonnull
    EmailNotification prepareAndSendResetPasswordEmail(@Nonnull final String toAddress, @Nonnull final ResetPasswordEmailTemplateModel resetPasswordEmailTemplateModel);
}
