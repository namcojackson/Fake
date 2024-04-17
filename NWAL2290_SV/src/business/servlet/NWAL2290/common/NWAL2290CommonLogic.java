/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2290.common;

import static business.servlet.NWAL2290.constant.NWAL2290Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2290.constant.NWAL2290Constant.BTN_CMN_CLO;
import business.servlet.NWAL2290.NWAL2290BMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/05   Fujitsu         A.Kosai         Create          N/A
 *</pre>
 */
public class NWAL2290CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLO[0], BTN_CMN_CLO[1], BTN_CMN_CLO[2], 1, null);
    }
}
