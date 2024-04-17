/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC301001;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import business.parts.NLZC301001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC301001.constant.NLZC301001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Warehouse Transfer API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/10/22   Hitachi         T.Aoyagi        Create          N/A
 * 2013/03/25   Hitachi         T.Aoyagi        Update          QC847
 * 2013/05/21   Hitachi         T.Aoyagi        Update          QC1224
 * 2015/10/07   CITS            H.Sugawara      Update          CSA Project
 *</pre>
 */
public class NLZC301001Util {

    /**
     * sort PMsg
     * @param list List<NLZC301001PMsg>
     */
    public void sort(List<NLZC301001PMsg> list) {

        String[] sortKeys = new String[10];
        sortKeys[0] = NLZC301001Constant.FROM_LOC_CD;
        sortKeys[1] = NLZC301001Constant.TO_LOC_CD;
        sortKeys[2] = NLZC301001Constant.DROP_SHIP_FLG;
        sortKeys[3] = NLZC301001Constant.SHIP_TO_CUST_CD;
        sortKeys[4] = NLZC301001Constant.SHPG_SVC_LVL_CD;
        sortKeys[5] = NLZC301001Constant.SO_TP_CD;
        sortKeys[6] = NLZC301001Constant.DELY_DT;
        sortKeys[7] = NLZC301001Constant.FSR_NUM;
        sortKeys[8] = NLZC301001Constant.PRCH_REQ_NUM;
        sortKeys[9] = NLZC301001Constant.PRCH_REQ_LINE_NUM;

        int[] orders = new int[10];
        orders[0] = NLZC301001Constant.ASC;
        orders[1] = NLZC301001Constant.ASC;
        orders[2] = NLZC301001Constant.DESC;
        orders[3] = NLZC301001Constant.ASC;
        orders[4] = NLZC301001Constant.ASC;
        orders[5] = NLZC301001Constant.ASC;
        orders[6] = NLZC301001Constant.ASC;
        orders[7] = NLZC301001Constant.ASC;
        orders[8] = NLZC301001Constant.ASC;
        orders[9] = NLZC301001Constant.ASC;
        Collections.sort(list, new Comp(sortKeys, orders));
    }

    /**
     * Comparator Class
     */
    private static class Comp implements Comparator<Object> {

        /**
         * sortKeys
         */
        private String[] sortKeys;

        /**
         * orders
         */
        private int[] orders;

        public Comp(String[] sortKeys, int[] orders) {
            this.sortKeys = sortKeys;
            this.orders = orders;
        }

        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {

            int c = 0;

            for (int idx = 0; idx < this.sortKeys.length; idx++) {
                String val1 = getValue(sortKeys[idx], oPMsg1);
                String val2 = getValue(sortKeys[idx], oPMsg2);
                if (orders[idx] == NLZC301001Constant.ASC) {
                    // ASC
                    c = compareTo(val1, val2);
                } else {
                    // DESC
                    c = compareTo(val2, val1);
                }

                if (c != 0) {
                    break;
                }
            }
            return c;
        }

        private String getValue(String key, Object oPMsg) {

            try {
                Field field = oPMsg.getClass().getField(key);

                if (NLZC301001Constant.DELY_DT.equals(key)) {
                    EZDPDateItem item = (EZDPDateItem) field.get(oPMsg);
                    return item.getValue();
                } else if (NLZC301001Constant.FSR_NUM.equals(key)) {
                    EZDPStringItem item = (EZDPStringItem) field.get(oPMsg);
                    if (!ZYPCommonFunc.hasValue(item)) {
                        return NLZC301001Constant.BLANK;
                    }
                    return ZYPCommonFunc.leftPad(item.getValue(),
                            NLZC301001Constant.LENGTH_FSR_NUM, NLZC301001Constant.ZERO);
                } else {
                    EZDPStringItem item = (EZDPStringItem) field.get(oPMsg);
                    return item.getValue();
                }
            } catch (Exception e) {
                return NLZC301001Constant.BLANK;
            }
        }

        private int compareTo(String val1, String val2) {
            if (ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
                return val1.compareTo(val2);
            } else {
                // ASC : move null value to end.
                // DESC : move null value to start.
                return val2.compareTo(val1);
            }
        }
    }
}
