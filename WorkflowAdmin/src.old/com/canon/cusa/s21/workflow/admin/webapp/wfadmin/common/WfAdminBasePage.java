package com.canon.cusa.s21.workflow.admin.webapp.wfadmin.common;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.workflow.common.sweb.page.WfSwebDestinationPage;
import com.canon.cusa.s21.framework.workflow.core.userprof.model.WfUser;
import com.canon.cusa.s21.framework.workflow.core.webapp.common.WfSwebBasePage;

/**
 * <p/>
 *   Workflow Admin Base Page
 * </p>
 *
 * @author $Author: Rohit Chandramohan $
 */
public abstract class WfAdminBasePage extends WfSwebBasePage {
    private static final S21Logger logger = S21LoggerFactory.getLogger(WfAdminBasePage.class);

    protected String action;

    //---------------------------------------------
    // Constructor
    //---------------------------------------------
    public WfAdminBasePage() {
    }

    //---------------------------
    // init
    //---------------------------
    public void initApplicationSpecificProperty() throws Exception {
        super.initApplicationSpecificProperty();
    }

    //---------------------------------------------
    // Pre Listeners
    //---------------------------------------------
    public void preListener() throws Exception {
        if (!isUserInRole("ZZWL8000T010")) {
            throw new WfAdminSecurityViolationException("No Permission for ZZWL8000T010");
        }
    }

    //---------------------------
    // security
    //---------------------------
    protected boolean isUserInRole(String roleId) {
        WfUser user = getLoginWfUser();
        if (user == null) {
            return false;
        } else {
            return wfUserProfileService.isUserInRole(user.getId(), roleId);
        }
    }

    //---------------------------
    // properties
    //---------------------------
    public WfUser getLoginWfUser() {
//        return AbstractWfUserSetupFilter.getLoginWfUser(request);
        return wfUserProfileService.getLoginUser();
    }

    /**
     * action property can be used as place holder to tag event-source 
     * associated with page events.
     * By using this technique all events within a page need to subscribe only to 
     * a single listener method. 
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter for action property
     * Action property can set to page as below: 
     * <input type="submit" name="action" value="Show">
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Implemented by individual admin pages
     *
     * @return
     */
    public abstract String getPageName();

    /**
     * Implemented by individual admin pages
     *
     * @return
     */
    public abstract String getPageId();

}
