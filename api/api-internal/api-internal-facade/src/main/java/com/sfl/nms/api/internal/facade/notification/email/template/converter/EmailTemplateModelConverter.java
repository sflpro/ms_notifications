package com.sfl.nms.api.internal.facade.notification.email.template.converter;

import com.sfl.nms.core.api.internal.model.email.template.EmailTemplateClientType;
import com.sfl.nms.services.notification.email.template.model.BaseEmailTemplateModel;

import javax.annotation.Nonnull;

/**
 * User: Davit Yeghiazaryan
 * Company: SFL LLC
 * Date 6/15/16
 * Time 5:32 PM
 */
public interface EmailTemplateModelConverter<T, M> extends ModelConverter<T, M> {

    /**
     * Gets email template type
     * @return EmailTemplateClientType
     */
    @Nonnull
    EmailTemplateClientType getEmailTemplateType();

    /**
     * Creates an empty email template model
     *
     * @return BaseEmailTemplateModel
     */
    @Nonnull
    BaseEmailTemplateModel createEmailTemplateModel();
}
