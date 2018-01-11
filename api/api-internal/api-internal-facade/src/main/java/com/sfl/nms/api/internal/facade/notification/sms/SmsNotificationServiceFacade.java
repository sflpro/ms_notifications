package com.sfl.nms.api.internal.facade.notification.sms;

import com.sfl.nms.core.api.internal.model.common.result.ResultResponseModel;
import com.sfl.nms.core.api.internal.model.sms.request.CreateSmsNotificationRequest;
import com.sfl.nms.core.api.internal.model.sms.response.CreateSmsNotificationResponse;

import javax.annotation.Nonnull;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/13/16
 * Time: 5:13 PM
 */
public interface SmsNotificationServiceFacade {

    /**
     * Creates email notification for provided request
     *
     * @param request
     * @return response
     */
    @Nonnull
    ResultResponseModel<CreateSmsNotificationResponse> createSmsNotification(@Nonnull final CreateSmsNotificationRequest request);
}
