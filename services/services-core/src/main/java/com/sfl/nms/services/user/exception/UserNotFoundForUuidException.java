package com.sfl.nms.services.user.exception;

import com.sfl.nms.services.common.exception.EntityNotFoundForUuIdException;
import com.sfl.nms.services.user.model.User;

/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 1/17/15
 * Time: 2:53 PM
 */
public class UserNotFoundForUuidException extends EntityNotFoundForUuIdException {

    private static final long serialVersionUID = -2552415605596284708L;

    /* Constructors */
    public UserNotFoundForUuidException(final String uuId) {
        super(uuId, User.class);
    }
}
