/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0520.common;

import java.math.BigDecimal;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NSAL0520.NSAL0520CMsg;
import business.blap.NSAL0520.NSAL0520SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0520CommonLogic {

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static void paginateTableA(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg, int startIndex) {
        paginate(cMsg.A, sMsg.A, startIndex);
        setTableAPaginationParameters(cMsg, sMsg, startIndex);
    }

    private static void paginate(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int startIndex) {
        int num = 0;
        ZYPTableUtil.clear(cMsgArray);
        for (int i = startIndex; i < sMsgArray.getValidCount(); i++) {
            if (num >= cMsgArray.length()) {
                break;
            }
            EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(num), null);
            num++;
        }
        cMsgArray.setValidCount(num);
    }

    private static void setTableAPaginationParameters(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg, int startIndex) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
    }

    public static String formatAddress(String... addrList) {
        StringBuilder buf = new StringBuilder();
        boolean firstColumn = true;
        if (addrList != null) {
            for (int i = 0; i < addrList.length; i++) {
                String addr = addrList[i];
                if (ZYPCommonFunc.hasValue(addr)) {
                    if (firstColumn) {
                        firstColumn = false;
                    } else {
                        buf.append(" ");
                    }
                    buf.append(addr);
                }
            }
        }
        return buf.toString();
    }

}
