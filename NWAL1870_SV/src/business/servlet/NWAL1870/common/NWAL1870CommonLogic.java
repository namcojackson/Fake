/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1870.common;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLS_BTN_NM;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLS_EVENT_NM;
import static business.servlet.NWAL1870.constant.NWAL1870Constant.BTN_CMN_CLS_LABEL;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1870CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1870CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLS_BTN_NM, BTN_CMN_CLS_EVENT_NM, BTN_CMN_CLS_LABEL, 0, null);
    }
}
