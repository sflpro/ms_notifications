package com.sfl.nms.persistence.repositories.notification;

import com.sfl.nms.services.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 3/21/15
 * Time: 7:54 PM
 */
public interface AbstractNotificationRepository<T extends Notification> extends JpaRepository<T, Long> {
}
