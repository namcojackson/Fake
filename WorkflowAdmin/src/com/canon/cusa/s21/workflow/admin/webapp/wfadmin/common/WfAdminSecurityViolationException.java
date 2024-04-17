/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: 2009/12/03
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common;

/**
 * <p>
 * </p>
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class WfAdminSecurityViolationException extends Exception {
    public WfAdminSecurityViolationException(String msg) {
        super(msg);
    }

    public WfAdminSecurityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
