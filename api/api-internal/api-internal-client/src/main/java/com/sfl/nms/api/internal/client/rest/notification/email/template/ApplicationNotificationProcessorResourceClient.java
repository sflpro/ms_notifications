package com.sfl.nms.api.internal.client.rest.notification.email.template;

import com.sfl.nms.core.api.internal.model.common.result.ResultResponseModel;
import com.sfl.nms.core.api.internal.model.email.response.CreateEmailNotificationResponse;
import com.sfl.nms.core.api.internal.model.email.template.request.ForgotPasswordRequest;

import javax.annotation.Nonnull;

/**
 * User: Davit Yeghiazaryan
 * Company: SFL LLC
 * Date 7/6/16
 * Time 11:42 AM
 */
public interface ApplicationNotificationProcessorResourceClient {

    /**
     * Process forgot password
     *
     * @param request
     * @return response
     */
    @Nonnull
    ResultResponseModel<CreateEmailNotificationResponse> processForgotPassword(@Nonnull final ForgotPasswordRequest request);
}
