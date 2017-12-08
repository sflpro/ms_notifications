package com.sfl.nms.api.internal.rest;

import com.sfl.nms.api.internal.rest.config.GenericJerseyConfig;
import com.sfl.nms.api.internal.rest.resources.maintanance.HeartBeatResource;
import com.sfl.nms.api.internal.rest.resources.notification.email.template.ApplicationNotificationProcessorResource;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * Company: SFL LLC
 * Created on 07/12/2017
 *
 * @author Davit Harutyunyan
 */
public class NotificationJerseyConfig extends GenericJerseyConfig {

    public NotificationJerseyConfig() {
        super();

        // Endpoints
        register(HeartBeatResource.class);
        register(ApplicationNotificationProcessorResource.class);

        // Filters
        register(LoggingFilter.class);
        register(NotificationJerseyConfig.class);
    }

}
