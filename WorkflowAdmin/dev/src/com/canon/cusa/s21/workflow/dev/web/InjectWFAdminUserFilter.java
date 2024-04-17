/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Rohit Chandramohan
 * Company: Fujitsu America
 * Date: Oct 07, 2009
 */
package com.canon.cusa.s21.workflow.dev.web;


import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper;
import com.canon.cusa.s21.framework.security.S21SecurityException;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter is temporary solution to inject a hardcoded user "WFADMTST" into session  for WF ADMIN applications test environment.
 * <p/>
 * This is work around to provide admin user id required by WF Admin application.
 * Once WFAdmin is integrated with S21Admin application the filter needs to be removed.
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class InjectWFAdminUserFilter implements Filter {

    private static final String WFADMIN_LOGIN_USER = "WFADMTST";

    private static final S21Logger logger = S21LoggerFactory.getLogger(InjectWFAdminUserFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Login as WF Admin for test aplication as a static test user
     */
    public void doFilter(ServletRequest request, ServletResponse resposne, FilterChain chain) throws IOException, ServletException {
        // make sure session is available for testing
        ((HttpServletRequest)request).getSession(true);

        try {
            S21UserProfileService s21UserProfileService = S21UserProfileServiceFactory.getInstance().getService();
            S21UserInfo user = s21UserProfileService.getContextUserInfo();
            if (user == null) {
                String securityConfiguration = "default";
                boolean isAuthenticated = S21AuthenticationHelper.loginUser(null, WFADMIN_LOGIN_USER, "canon", securityConfiguration);
                if (!isAuthenticated) {
                    logger.error("InjectWFAdminUserFilter: Cannot login user  " + WFADMIN_LOGIN_USER);
                    throw new ServletException("InjectWFAdminUserFilter: Cannot login user  " + WFADMIN_LOGIN_USER);
                } else {
                    logger.info("InjectWFAdminUserFilter: loged in user  " + WFADMIN_LOGIN_USER);
                }
            }

            chain.doFilter(request, resposne);

        } catch (S21SecurityException e) {
            logger.error("InjectWFAdminUserFilter: S21SecurityException for " + WFADMIN_LOGIN_USER);
            throw new ServletException("InjectWFAdminUserFilter: S21SecurityException  " + WFADMIN_LOGIN_USER, e);
        }
    }

    public void destroy() {
    }

}
