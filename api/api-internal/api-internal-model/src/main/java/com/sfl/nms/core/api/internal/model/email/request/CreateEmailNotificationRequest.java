package com.sfl.nms.core.api.internal.model.email.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.nms.core.api.internal.model.common.result.ErrorResponseModel;
import com.sfl.nms.core.api.internal.model.common.result.ErrorType;
import com.sfl.nms.core.api.internal.model.notification.request.AbstractCreateNotificationRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/12/16
 * Time: 7:36 PM
 */
public class CreateEmailNotificationRequest extends AbstractCreateNotificationRequest {

    private static final long serialVersionUID = 8423183227450211673L;

    /* Properties */
    @JsonProperty("recipientEmail")
    private String recipientEmail;

    @JsonProperty("senderEmail")
    private String senderEmail;

    @JsonProperty("subject")
    private String subject;

    /* Constructors */
    public CreateEmailNotificationRequest() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /* Validation methods */
    @Nonnull
    @Override
    public List<ErrorResponseModel> validateRequiredFields() {
        final List<ErrorResponseModel> errors = super.validateRequiredFields();
        if (StringUtils.isBlank(recipientEmail)) {
            errors.add(new ErrorResponseModel(ErrorType.NOTIFICATION_EMAIL_RECIPIENT_ADDRESS_MISSING));
        }
        if (StringUtils.isBlank(senderEmail)) {
            errors.add(new ErrorResponseModel(ErrorType.NOTIFICATION_EMAIL_SENDER_ADDRESS_MISSING));
        }
        return errors;
    }

    /* Equals, HashCode and ToString */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateEmailNotificationRequest)) {
            return false;
        }
        final CreateEmailNotificationRequest that = (CreateEmailNotificationRequest) o;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(o));
        builder.append(this.getSenderEmail(), that.getSenderEmail());
        builder.append(this.getRecipientEmail(), that.getRecipientEmail());
        builder.append(this.getSubject(), that.getSubject());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(this.getSenderEmail());
        builder.append(this.getRecipientEmail());
        builder.append(this.getSubject());
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.appendSuper(super.toString());
        builder.append("senderEmail", this.getSenderEmail());
        builder.append("recipientEmail", this.getRecipientEmail());
        builder.append("subject", this.getSubject());
        return builder.build();
    }
}
