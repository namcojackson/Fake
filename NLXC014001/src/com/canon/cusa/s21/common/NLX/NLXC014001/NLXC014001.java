/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.common.NLX.NLXC014001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/06/2009   Fujitsu         H.Haga           Create          N/A
 *</pre>
 */
public final class NLXC014001 {

    /**
     */
    private NLXC014001() {
    }

    /**
     */
    public static String nullToEmpty(final String str) {

        if (str == null) {
            return "";
        } else {
            return str;
        }

    }

    /**
     */
    public static BigDecimal nullToZero(BigDecimal val) {

        if (val == null) {
            return BigDecimal.ZERO;
        } else {
            return val;
        }

    }

    /**
     */
    public static String getStringLeftPadZero(int value, int length) {

        return getStringLeftPadZero(new BigDecimal(value), length);
    }

    /**
     */
    public static String getStringLeftPadZero(BigDecimal value, int length) {

        return ZYPCommonFunc.leftPad(value.toPlainString(), length, "0");
    }

    /**
     */
    public static boolean getMesRtn(EZDPMsg msgMap) {

        List<String> errList = S21ApiUtil.getXxMsgIdList(msgMap);
        if (errList.isEmpty() == false) {
            for (String xxMsgId : errList) {
                if (xxMsgId.endsWith("E") == true) {
                    return false;
                }
            }
        }

        return true;

    }
}
