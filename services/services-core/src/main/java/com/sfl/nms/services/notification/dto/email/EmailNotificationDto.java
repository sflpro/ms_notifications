package com.sfl.nms.services.notification.dto.email;

import com.sfl.nms.services.notification.model.NotificationType;
import com.sfl.nms.services.notification.dto.NotificationDto;
import com.sfl.nms.services.notification.model.NotificationProviderType;
import com.sfl.nms.services.notification.model.email.EmailNotification;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 3/21/15
 * Time: 7:29 PM
 */
public class EmailNotificationDto extends NotificationDto<EmailNotification> {
    private static final long serialVersionUID = -2505594013054500474L;

    /* Properties */
    private String recipientEmail;

    private String senderEmail;

    private NotificationProviderType providerType;

    /* Constructors */
    public EmailNotificationDto(final String recipientEmail, final String senderEmail, final NotificationProviderType providerType, final String content, final String subject, final String clientIpAddress) {
        super(NotificationType.EMAIL, content, subject, clientIpAddress);
        this.recipientEmail = recipientEmail;
        this.senderEmail = senderEmail;
        this.providerType = providerType;
    }

    public EmailNotificationDto() {
        super(NotificationType.EMAIL);
    }

    /* Properties getters and setters */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(final String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public NotificationProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(final NotificationProviderType providerType) {
        this.providerType = providerType;
    }

    /* Public interface methods */
    @Override
    public void updateDomainEntityProperties(final EmailNotification notification) {
        super.updateDomainEntityProperties(notification);
        notification.setRecipientEmail(getRecipientEmail());
        notification.setProviderType(getProviderType());
        notification.setSenderEmail(getSenderEmail());
    }

    /* Equals, HashCode and ToString */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailNotificationDto)) {
            return false;
        }
        final EmailNotificationDto that = (EmailNotificationDto) o;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(that));
        builder.append(this.getRecipientEmail(), that.getRecipientEmail());
        builder.append(this.getSenderEmail(), that.getSenderEmail());
        builder.append(this.getProviderType(), that.getProviderType());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(this.getRecipientEmail());
        builder.append(this.getSenderEmail());
        builder.append(this.getProviderType());
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.appendSuper(super.toString());
        builder.append("recipientEmail", this.getRecipientEmail());
        builder.append("senderEmail", this.getSenderEmail());
        builder.append("providerType", this.getProviderType());
        return builder.build();
    }
}
