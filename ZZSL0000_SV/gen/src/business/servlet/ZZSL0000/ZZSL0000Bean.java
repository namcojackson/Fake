/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: ${user}
 * Company: Fujitsu Limited
 * Date: ${date}
 */

package business.servlet.ZZSL0000;

import parts.servletcommon.EZDCommonDataBean;

/**
 * ZZSL0000Bean is a data bean class.
 * @author $Author: Katsutoshi Takahashi $ Arti Kumari
 * @version $Revision: 1.4 $ $Date: 2009/07/23 15:57:48 $
 */
public class ZZSL0000Bean extends EZDCommonDataBean {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    // Constant Definition Area
    /** loginID */
    public static final String loginID = "loginID";

    /** loginPW */
    public static final String loginPW = "loginPW";

    /** _EZBusinessApplicationID */
    public static final String ezBusinessID = "ezBusinessID";

    /**
     * ZZSL0000Bean is Bean's Constructor. This method does the
     * initialization when instance of ZZSL0000Bean is generated.
     */
    public ZZSL0000Bean() {
        super("business.servlet.ZZSL0000.ZZSL0000BMsg");
    }

    /**
     * get loginID.
     * @return loginID
     */
    public String getLoginID() {
        return outputValue(loginID);
    }

    /**
     * set loginID.
     * @param data loginID
     */
    public void setLoginID(String data) {
        inputValue(loginID, data);
    }

    /**
     * get loginPW.
     * @return loginPW
     */
    public String getLoginPW() {
        return outputValue(loginPW);
    }

    /**
     * set loginPW.
     * @param data loginPW
     */
    public void setLoginPW(String data) {
        inputValue(loginPW, data);
    }

    /**
     * get  _EZBusinessApplicationID.
     * @return _EZBusinessApplicationID
     */
    public String getEzBusinessID() {
        return outputValue(ezBusinessID);
    }

    /**
     * set  _EZBusinessApplicationID.
     * @param data _EZBusinessApplicationID
     */
    public void setEzBusinessID(String data) {
        inputValue(ezBusinessID,data);
    }
}
