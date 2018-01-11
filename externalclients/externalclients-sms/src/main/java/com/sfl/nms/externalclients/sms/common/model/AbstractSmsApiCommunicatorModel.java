package com.sfl.nms.externalclients.sms.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Mher Sargsyan
 * Company: SFL LLC
 * Date: 4/9/15
 * Time: 3:32 PM
 */
public abstract class AbstractSmsApiCommunicatorModel implements Serializable {

    private static final long serialVersionUID = 8816797216339854212L;

    /* Constructors */
    public AbstractSmsApiCommunicatorModel() {
    }


    /* Equals, HashCode and ToString */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractSmsApiCommunicatorModel)) {
            return false;
        }
        final EqualsBuilder builder = new EqualsBuilder();
        return builder.build();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        return builder.build();
    }
}
