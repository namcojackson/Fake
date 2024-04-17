/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Tak Yoshida
 * Company: SRA AMERICA, Inc.
 * Date: Jan 9, 2009
 */
package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.menu;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebPageTransitionContext;
import com.canon.cusa.s21.framework.workflow.core.exception.S21WfSecurityViolationException;
import com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common.WfAdminBasePage;

/**
 * <p>
 * </p>
 *
 * @author $Author: MasahitoNakahara $
 * @version $Revision: 1.5 $ $Date: 2009/04/28 16:06:35 $
 */
public class MenuPage extends WfAdminBasePage {
	private static final S21Logger logger = S21LoggerFactory.getLogger(MenuPage.class);
	private static final String SHORT_CLASS_NAME = S21StringUtil.getShortClassName(MenuPage.class.getName());

	
	//---------------------------
	// preparation
	//---------------------------
	public void prepareRequiredProperties(WfSwebPageTransitionContext pageTransitionContext) throws Exception {
    }

    //---------------------------------------------
	// Listeners
	//---------------------------------------------
	public WfSwebDestinationPage showPage() throws Exception {
		logger.debug("showPage...");
		return activateDestinationPageWithoutLayout(getClass());
	}
	// just for UI comp compatibility
	public String getUserId() {
		try {
			return getLoginUserId();
		} catch (S21WfSecurityViolationException e) {
			return "no-user";
		}
	}

	@Override
	public String getPageId() {
		return "ZZWL8000";
	}

	@Override
	public String getPageName() {
		return "Workflow Administration Menu";
	}

}
