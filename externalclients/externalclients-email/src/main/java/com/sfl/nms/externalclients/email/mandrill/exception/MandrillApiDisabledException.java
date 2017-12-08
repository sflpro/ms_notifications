package com.sfl.nms.externalclients.email.mandrill.exception;

/**
 * Company: SFL LLC
 * Created on 08/12/2017
 *
 * @author Davit Harutyunyan
 */
public class MandrillApiDisabledException extends MandrillEmailClientRuntimeException {

    public MandrillApiDisabledException() {
        super("Mandrill Api disabled, please provide api token");
    }
}
