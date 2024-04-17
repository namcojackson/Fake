/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2560.common;

import parts.common.EZDMsg;
import business.blap.NMAL2560.NMAL2560CMsg;
import business.blap.NMAL2560.NMAL2560SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * NMAL2560CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2560CommonLogic {

    /**
     * formatDt14ToDisp
     * @param dt date
     * @return after format date
     */
    public static String formatDt14ToDisp(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > 14) {
            dt = dt.substring(0, 14);
        }
        return ZYPDateUtil.formatEzd14ToDisp(dt);
    }

    /**
     * formatEzd8ToDisp
     * @param dt date
     * @return after format date
     */
    public static String formatEzd8ToDisp(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > 8) {
            dt = dt.substring(0, 8);
        }
        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL2560CMsg
     * @param glblMsg NMAL2560SMsg
     */
    public static void updateGlblMsg(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        glblMsg.clearErrorInfo();
        glblMsg.A.clearErrorInfo();

        EZDMsg.copy(bizMsg.A, null, glblMsg.A, null);
    }
}
