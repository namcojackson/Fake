package business.servlet.ZZIL0050.common;

import business.servlet.ZZIL0050.constant.ZZIL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0050CommonLogic {

    /**
     * setCommonButtons
     * @param   handler S21CommonHandler
     */
    public static final void setCommonButtons(S21CommonHandler handler) {
        handler.setButtonProperties(ZZIL0050Constant.BTN_CMN_CLEAR[0], ZZIL0050Constant.BTN_CMN_CLEAR[1],
                ZZIL0050Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(ZZIL0050Constant.BTN_CMN_CLOSE[0], ZZIL0050Constant.BTN_CMN_CLOSE[1],
                ZZIL0050Constant.BTN_CMN_CLOSE[2], 1, null);
    }

}
