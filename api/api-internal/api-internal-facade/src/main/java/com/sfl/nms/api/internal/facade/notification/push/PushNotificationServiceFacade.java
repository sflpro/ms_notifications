package com.sfl.nms.api.internal.facade.notification.push;

import com.sfl.nms.core.api.internal.model.common.result.ResultResponseModel;
import com.sfl.nms.core.api.internal.model.push.request.CreatePushNotificationRequest;
import com.sfl.nms.core.api.internal.model.push.request.UpdatePushNotificationSubscriptionRequest;
import com.sfl.nms.core.api.internal.model.push.response.CreatePushNotificationResponse;
import com.sfl.nms.core.api.internal.model.push.response.UpdatePushNotificationSubscriptionResponse;

import javax.annotation.Nonnull;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/13/16
 * Time: 6:13 PM
 */
public interface PushNotificationServiceFacade {

    /**
     * Create push notifications
     *
     * @param request
     * @return result
     */
    @Nonnull
    ResultResponseModel<CreatePushNotificationResponse> createPushNotifications(@Nonnull final CreatePushNotificationRequest request);


    /**
     * Updates push notification subscription
     *
     * @param request
     * @return result
     */
    @Nonnull
    ResultResponseModel<UpdatePushNotificationSubscriptionResponse> updatePushNotificationSubscription(@Nonnull final UpdatePushNotificationSubscriptionRequest request);
}
