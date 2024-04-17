package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant.MSG_ID;
import com.canon.cusa.s21.framework.log.S21AbendException;

/** <pre>
 * NWXC220001Util
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWXC220001Util {

    public static String convNumToZ99(int num) {
//        int div = num / 100;
//        int mod = num % 100;
//
//        return String.format("%s%02d", NWXC220001Constant.ZERO_TO_Z.substring(div, div+1), mod);

        if (num < NWXC220001Constant.THOUSAND) {
            int div = num / 100;
            int mod = num % 100;

            return String.format("%s%02d", NWXC220001Constant.ZERO_TO_Z.substring(div, div+1), mod);
        } else if (num > NWXC220001Constant.MAX_LINE_NUM) {
            // overflow
            return null;
        } else {
            if (num == NWXC220001Constant.THOUSAND) {
                return NWXC220001Constant.FIRST_STR;
            }

            int sourceNumber = num - NWXC220001Constant.THOUSAND;
            int tempNumber = 0;
            StringBuilder rsVal = new StringBuilder();

            // Calculation Hundreds Place
            int hundredsPlace = sourceNumber / (NWXC220001Constant.BASE_NUMBER_36 * NWXC220001Constant.BASE_NUMBER_36);
            tempNumber = sourceNumber % (NWXC220001Constant.BASE_NUMBER_36 * NWXC220001Constant.BASE_NUMBER_36);
            rsVal.append(NWXC220001Constant.A_TO_Z.substring(hundredsPlace, hundredsPlace + 1));

            // Calculation Tens Place
            int tensPlace = tempNumber / NWXC220001Constant.BASE_NUMBER_36;
            tempNumber = tempNumber % NWXC220001Constant.BASE_NUMBER_36;
            rsVal.append(NWXC220001Constant.ZERO_TO_Z.substring(tensPlace, tensPlace + 1));

            // Calculation Ones Place
            int onePlace = tempNumber;
            rsVal.append(NWXC220001Constant.ZERO_TO_Z.substring(onePlace, onePlace + 1));

            return rsVal.toString();
        }
    }

    public static int convZZ9ToNum(String z99) {
//        int div = NWXC220001Constant.ZERO_TO_Z.indexOf(z99.substring(0, 1));
//        int mod = Integer.valueOf(z99.substring(1));
//
//        return div * 100 + mod;
        
        int z99First = z99.charAt(0);
        if(z99First >= '0' && z99First <= '9') {
            return Integer.valueOf(z99);
        } else {
            int rsVal = NWXC220001Constant.THOUSAND;
            char hundredsPlaceStr = z99.charAt(0);
            rsVal = rsVal + NWXC220001Constant.A_TO_Z.indexOf(hundredsPlaceStr)
                                      * NWXC220001Constant.BASE_NUMBER_36
                                      * NWXC220001Constant.BASE_NUMBER_36;

            char tensPlaceStr = z99.charAt(1);
            rsVal = rsVal + NWXC220001Constant.ZERO_TO_Z.indexOf(tensPlaceStr)
                                      * NWXC220001Constant.BASE_NUMBER_36;

            char onesPlaceStr = z99.charAt(2);
            rsVal = rsVal + NWXC220001Constant.ZERO_TO_Z.indexOf(onesPlaceStr);

            return rsVal;
        }
    }

    public static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    public static <T> boolean isTargetContents(T target, T... valParam) {
        return Arrays.asList(valParam).contains(target);
    }

    public static <T> boolean isTargetsContentsList(List<T> valList, T... targets) {
        for (int i = 0; i < targets.length; i++) {
            if (valList.contains(targets[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Convert To String
     * @param val Object
     * @return String
     */
    public static String convToString(Object val) {
        return convToString(val, null);
    }

    public static String convToString(Object val, String defVal) {
        if (null == val) {
            return defVal;
        } else {
            return val.toString();
        }
    }

    public static BigDecimal convToBigDecimal(Object val) {
        return convToBigDecimal(val, null);
    }

    public static BigDecimal convToBigDecimal(Object val, BigDecimal defVal) {
        if (null == val) {
            if (defVal != null) {
                return defVal;
            }
            return null;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof EZDBStringItem) {
            return convToBigDecimal(((EZDBStringItem) val).getValue());
        } else if (val instanceof EZDBBigDecimalItem) {
            return ((EZDBBigDecimalItem) val).getValue();
        } else if (val instanceof EZDCStringItem) {
            return convToBigDecimal(((EZDCStringItem) val).getValue());
        } else if (val instanceof EZDTStringItem) {
            return convToBigDecimal(((EZDTStringItem) val).getValue());
        } else if (val instanceof EZDCBigDecimalItem) {
            return ((EZDCBigDecimalItem) val).getValue();
        } else if (val instanceof EZDTBigDecimalItem) {
            return ((EZDTBigDecimalItem) val).getValue();
        } else {
            return new BigDecimal(val.toString());
        }
    }

    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    public static <T> T[] toArray(T... param) {
        return param;
    }

    public static boolean checkTMsgDbAccess(EZDTMsg tMsg) {
        return checkTMsgDbAccess(tMsg, true);
    }

    public static boolean checkTMsgDbAccess(EZDTMsg tMsg, boolean doThrowErr) {
        if (tMsg != null && !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            if (doThrowErr) {
                throw new S21AbendException(MSG_ID.NMAM0283E.toString());
            }
            return false;
        }

        return true;
    }

}
