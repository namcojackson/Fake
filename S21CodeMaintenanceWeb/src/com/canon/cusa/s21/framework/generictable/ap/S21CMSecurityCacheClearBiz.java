package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEConfig;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.security.as.common.icache.flash.S21AuthorizationCacheAccessor;

/**
 * Code Maintenance Screen.
 * Security cache clear processing.
 * @author Administrator
 */
public class S21CMSecurityCacheClearBiz extends S21CMCacheClearBaseBiz {

    /* (non-Javadoc)
     * @see com.canon.cusa.s21.framework.generictable.ap.S21CMCacheClearBaseBiz#cacheClear(com.canon.cusa.s21.framework.generictable.ap.S21CMSearchInputBean)
     */
    @Override
    protected void cacheClear(S21CMSearchInputBean sib) {
        String appName = S21NEConfig.getInstance().getAppName();
        S21InfoLogOutput.println("S21NECacheClearServlet:Received APP Name ="+appName);
        S21AuthorizationCacheAccessor.getInstance().invalidateCache(appName);
        S21InfoLogOutput.println("S21NECacheClearServlet:"+appName+" cache was cleared.");
    }

}
