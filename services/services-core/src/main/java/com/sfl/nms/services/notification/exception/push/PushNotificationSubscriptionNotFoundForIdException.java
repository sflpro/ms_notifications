package com.sfl.nms.services.notification.exception.push;

import com.sfl.nms.services.common.exception.EntityNotFoundForIdException;
import com.sfl.nms.services.notification.model.push.PushNotificationSubscription;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 8/13/15
 * Time: 10:45 AM
 */
public class PushNotificationSubscriptionNotFoundForIdException extends EntityNotFoundForIdException {

    private static final long serialVersionUID = 6331583001630717942L;

    public PushNotificationSubscriptionNotFoundForIdException(final Long subscriptionId) {
        super(subscriptionId, PushNotificationSubscription.class);
    }
}


