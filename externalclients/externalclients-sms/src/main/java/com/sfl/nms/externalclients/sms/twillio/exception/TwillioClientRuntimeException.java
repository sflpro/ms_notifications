package com.sfl.nms.externalclients.sms.twillio.exception;

import com.sfl.nms.externalclients.sms.common.exception.ExternalSmsClientRuntimeException;
import com.twilio.sdk.TwilioRestException;

/**
 * User: Mher Sargsyan
 * Company: SFL LLC
 * Date: 4/9/15
 * Time: 7:42 PM
 */
public class TwillioClientRuntimeException extends ExternalSmsClientRuntimeException {

    private static final long serialVersionUID = 3157858197834455252L;

    /* Constructors */
    public TwillioClientRuntimeException(final String senderNumber, final String recipientNumber, final String messageBody, final TwilioRestException originalException) {
        super(senderNumber, recipientNumber, messageBody, originalException);
    }
}
