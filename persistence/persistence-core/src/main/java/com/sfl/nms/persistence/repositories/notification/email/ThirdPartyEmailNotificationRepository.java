package com.sfl.nms.persistence.repositories.notification.email;

import com.sfl.nms.persistence.repositories.notification.AbstractNotificationRepository;
import com.sfl.nms.services.notification.model.email.ThirdPartyEmailNotification;
import org.springframework.stereotype.Repository;

/**
 * User: Davit Yeghiazaryan
 * Company: SFL LLC
 * Date 6/30/16
 * Time 5:23 PM
 */
@Repository
public interface ThirdPartyEmailNotificationRepository extends AbstractNotificationRepository<ThirdPartyEmailNotification> {
}
